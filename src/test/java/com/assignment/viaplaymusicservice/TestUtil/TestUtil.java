package com.assignment.viaplaymusicservice.TestUtil;

import com.assignment.viaplaymusicservice.dto.Album;
import com.assignment.viaplaymusicservice.dto.ArtistDetailsResponse;
import com.assignment.viaplaymusicservice.dto.ArtistRelation;
import com.assignment.viaplaymusicservice.dto.CoverArtImage;
import com.assignment.viaplaymusicservice.dto.CoverArtResponse;
import com.assignment.viaplaymusicservice.dto.DiscogResponse;
import com.assignment.viaplaymusicservice.dto.MusicBrainzResponse;
import com.assignment.viaplaymusicservice.dto.ReleaseAlbum;
import com.assignment.viaplaymusicservice.dto.ResourceUrl;
import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static ArtistDetailsResponse buildArtistDetailsResponse(String mbId, DiscogResponse discogResponse) {
        ArtistDetailsResponse artistDetailsResponse = new ArtistDetailsResponse();
        artistDetailsResponse.setMbId(mbId);
        artistDetailsResponse.setDescription(discogResponse.getProfile());
        artistDetailsResponse.setAlbums(buildAlbums());
        return artistDetailsResponse;
    }

    public static DiscogResponse buildDiscogResponse() {
        DiscogResponse discogResponse = new DiscogResponse();
        discogResponse.setProfile("Profile description");
        return discogResponse;
    }

    public static CoverArtResponse buildCoverArtResponse() {
        CoverArtResponse coverArtResponse = new CoverArtResponse();
        List<CoverArtImage> images = new ArrayList<>();
        CoverArtImage coverArtImage = new CoverArtImage();
        coverArtImage.setImage("image url");
        images.add(coverArtImage);
        coverArtResponse.setImages(images);
        coverArtResponse.setReleaseUrl("some sample release url");
        return coverArtResponse;
    }

    public static MusicBrainzResponse buildMusicBrainzResponse() {
        MusicBrainzResponse musicBrainzResponse = new MusicBrainzResponse();
        List<ReleaseAlbum> releaseAlbums = buildReleaseAlbums();
        List<ArtistRelation> relations = buildArtistRelations();
        musicBrainzResponse.setReleaseGroups(releaseAlbums);
        musicBrainzResponse.setRelations(relations);
        return musicBrainzResponse;
    }

    private static List<ReleaseAlbum> buildReleaseAlbums() {
        List<ReleaseAlbum> releaseAlbums = new ArrayList<>();
        ReleaseAlbum releaseAlbum = new ReleaseAlbum();
        releaseAlbum.setId("id");
        releaseAlbum.setTitle("album-title");
        releaseAlbums.add(releaseAlbum);
        return releaseAlbums;
    }

    private static List<ArtistRelation> buildArtistRelations() {
        List<ArtistRelation> relations = new ArrayList<>();
        ArtistRelation artistRelation = new ArtistRelation();
        ResourceUrl resourceUrl = new ResourceUrl();
        resourceUrl.setRelationResource("relation-resource-url");
        artistRelation.setResourceUrl(resourceUrl);
        artistRelation.setType("discog");
        relations.add(artistRelation);
        return relations;
    }

    private static List<Album> buildAlbums() {
        List<Album> albums = new ArrayList<>();
        Album album = new Album();
        album.setId("id");
        album.setTitle("album-title");
        album.setImageUrl("image url");
        albums.add(album);
        return albums;
    }

}
