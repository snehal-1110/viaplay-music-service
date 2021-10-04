package com.assignment.viaplaymusicservice.service;

import com.assignment.viaplaymusicservice.client.CoverArtClient;
import com.assignment.viaplaymusicservice.client.DiscogClient;
import com.assignment.viaplaymusicservice.client.MusicBrainzClient;
import com.assignment.viaplaymusicservice.dto.*;
import com.assignment.viaplaymusicservice.exception.ViaplayServiceDataException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ViaplayAlbumService {

    private final MusicBrainzClient musicBrainzClient;
    private final DiscogClient discogClient;
    private final CoverArtClient coverArtClient;
    private final String apiName;

    public ViaplayAlbumService(MusicBrainzClient musicBrainzClient, DiscogClient discogClient,
                               CoverArtClient coverArtClient, @Value("${apiName}") String apiName) {
        this.musicBrainzClient = musicBrainzClient;
        this.discogClient = discogClient;
        this.coverArtClient = coverArtClient;
        this.apiName = apiName;
    }

    public ArtistDetailsResponse getArtistDetailsResponse(String mbId) {
        ArtistDetailsResponse artistDetailsResponse = new ArtistDetailsResponse();
        MusicBrainzResponse musicBrainzResponse = musicBrainzClient.getArtistDetails(mbId);

        if (ObjectUtils.isEmpty(musicBrainzResponse)) {
            throw new ViaplayServiceDataException("Music brainz response is empty");
        }

        List<Album> albums = enrichArtistAlbumDetails(musicBrainzResponse);
        artistDetailsResponse.setAlbums(albums);
        artistDetailsResponse.setMbId(mbId);

        ArtistRelation artistRelation = findArtistRelation(musicBrainzResponse.getRelations());
        String discogId = extractDiscogId(artistRelation);

        DiscogResponse discogResponse = discogClient.getDiscogResponse(discogId);
        artistDetailsResponse.setDescription(discogResponse.getProfile());

        return artistDetailsResponse;
    }

    private List<Album> enrichArtistAlbumDetails(MusicBrainzResponse musicBrainzResponse) {
        List<ReleaseAlbum> releaseAlbums = musicBrainzResponse.getReleaseGroups();
        List<Album> albums = new ArrayList<>();

        releaseAlbums.forEach(releaseAlbum -> {
            Album album = new Album();
            album.setId(releaseAlbum.getId());
            album.setTitle(releaseAlbum.getTitle());

            CoverArtResponse coverArtResponse = coverArtClient.getCoverArtResponse(releaseAlbum.getId());

            Optional<CoverArtImage> coverArtImage = coverArtResponse.getImages().stream().findFirst();
            coverArtImage.ifPresent(artImage -> album.setImageUrl(artImage.getImage()));
            albums.add(album);
        });
        return albums;
    }

    private ArtistRelation findArtistRelation(List<ArtistRelation> artistRelations) {
        return artistRelations
                .stream().filter(relation -> relation.getType().equals(apiName))
                .findFirst()
                .orElseThrow();
    }

    private String extractDiscogId(ArtistRelation artistRelation) {
        return StringUtils.substringAfterLast(artistRelation.getResourceUrl().getRelationResource(), "/");
    }

}
