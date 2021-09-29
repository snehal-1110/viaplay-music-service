package com.assignment.viaplaymusicservice.service;

import com.assignment.viaplaymusicservice.client.MusicBrainzClient;
import com.assignment.viaplaymusicservice.dto.Album;
import com.assignment.viaplaymusicservice.dto.ArtistDetailsResponse;
import com.assignment.viaplaymusicservice.dto.ArtistReleaseAlbumResponse;
import com.assignment.viaplaymusicservice.dto.ReleaseAlbum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViaplayAlbumService {

    private final MusicBrainzClient musicBrainzClient;

    public ViaplayAlbumService(MusicBrainzClient musicBrainzClient) {
        this.musicBrainzClient = musicBrainzClient;
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


        //artistDetailsResponse.setDescription();

        return artistDetailsResponse;
    }

}
