package com.assignment.viaplaymusicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReleaseAlbum {

    @JsonProperty("primary-type")
    private String primaryType;

    @JsonProperty("id")
    private String id;

    @JsonProperty("primary-type-id")
    private String primaryTypeId;

    @JsonProperty("secondary-types")
    private List<String> secondaryType;

    @JsonProperty("title")
    private String title;

    @JsonProperty("secondary-type-ids")
    private List<String> secondaryTypeIds;

    @JsonProperty("disambiguation")
    private String disambiguation;

    @JsonProperty("first-release-date")
    private String firstReleaseDate;

}

