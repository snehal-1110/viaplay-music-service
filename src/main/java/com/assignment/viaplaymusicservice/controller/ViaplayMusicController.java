package com.assignment.viaplaymusicservice.controller;

import com.assignment.viaplaymusicservice.dto.ArtistDetailsResponse;
import com.assignment.viaplaymusicservice.service.ViaplayAlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ViaplayMusicController {

    private final ViaplayAlbumService viaplayAlbumService;

    public ViaplayMusicController(ViaplayAlbumService viaplayAlbumService) {
        this.viaplayAlbumService = viaplayAlbumService;
    }

    @GetMapping(value = "/album/{mbId}")
    public ResponseEntity<ArtistDetailsResponse> getAlbumInfo(@PathVariable String mbId) {
        ArtistDetailsResponse artistDetailsResponse = viaplayAlbumService.getArtistDetailsResponse(mbId);
        return new ResponseEntity<ArtistDetailsResponse>(artistDetailsResponse, HttpStatus.OK);
    }
}
