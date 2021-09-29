package com.assignment.viaplaymusicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoverArtImage {

    @JsonProperty("image")
    private String image;

    @JsonProperty("front")
    private String front;
}
