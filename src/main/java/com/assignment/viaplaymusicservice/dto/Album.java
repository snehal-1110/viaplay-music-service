package com.assignment.viaplaymusicservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @JsonProperty("title")
    private String title;

    @JsonProperty("id")
    private String id;

    @JsonProperty("image")
    private String imageUrl;
}
