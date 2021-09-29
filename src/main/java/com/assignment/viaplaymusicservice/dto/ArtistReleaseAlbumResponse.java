package com.assignment.viaplaymusicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistReleaseAlbumResponse {

    @JsonProperty("release-groups")
    private List<ReleaseAlbum> releaseGroups;

    @JsonProperty("relations")
    private List<ArtistRelation> relations;

}
