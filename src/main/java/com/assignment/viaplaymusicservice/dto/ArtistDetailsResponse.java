package com.assignment.viaplaymusicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDetailsResponse {

    @JsonProperty("mbid")
    private String mbId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("albums")
    private List<Album> albums;

}
