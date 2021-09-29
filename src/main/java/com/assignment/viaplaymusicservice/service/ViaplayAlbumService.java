package com.assignment.viaplaymusicservice.service;

import com.assignment.viaplaymusicservice.client.DiscogClient;
import com.assignment.viaplaymusicservice.client.MusicBrainzClient;
import com.assignment.viaplaymusicservice.dto.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaplayAlbumService {

    private final MusicBrainzClient musicBrainzClient;

    private final DiscogClient discogClient;

    private final String apiName;

    public ViaplayAlbumService(MusicBrainzClient musicBrainzClient, DiscogClient discogClient,
                               @Value("${apiName}") String apiName) {
        this.musicBrainzClient = musicBrainzClient;
        this.discogClient = discogClient;
        this.apiName = apiName;
    }

    public ArtistDetailsResponse getArtistDetailsResponse(String mbId) {
        ArtistDetailsResponse artistDetailsResponse = new ArtistDetailsResponse();
        ArtistReleaseAlbumResponse artistReleaseAlbumResponse = musicBrainzClient.getArtistDetails(mbId);

        List<ReleaseAlbum> releaseAlbums = artistReleaseAlbumResponse.getReleaseGroups();
        List<Album> albums = new ArrayList<>();
        releaseAlbums.forEach(releaseAlbum -> {
            Album album = new Album();
            album.setId(releaseAlbum.getId());
            album.setTitle(releaseAlbum.getTitle());
            albums.add(album);
        });
        artistDetailsResponse.setAlbums(albums);

        artistDetailsResponse.setMbId(mbId);

        ArtistRelation artistRelation = getArtistRelation(artistReleaseAlbumResponse.getRelations());


        String discogId = extractDiscogId(artistRelation);
        String description = discogClient.getDiscogResponse(discogId).getProfile();

        artistDetailsResponse.setDescription(description);

        return artistDetailsResponse;
    }

    private ArtistRelation getArtistRelation(List<ArtistRelation> artistRelations) {
        return artistRelations
                .stream().filter(relation -> relation.getType().equals(apiName))
                .findFirst()
                .orElseThrow();
    }

    private String extractDiscogId(ArtistRelation artistRelation) {
        return StringUtils.substringAfterLast(artistRelation.getResourceUrl().getRelationResource(), "/");
    }

}
