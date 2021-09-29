package com.assignment.viaplaymusicservice.client;

import com.assignment.viaplaymusicservice.dto.ArtistReleaseAlbumResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MusicBrainzClient {

    private final WebClient webClient;
    private final String path;

    public MusicBrainzClient(WebClient.Builder webClientBuilder, @Value("${music-brainz.base-url}") String baseUrl,
                             @Value("${music-brainz.path}") String path) {
        this.path = path;
        this.webClient = webClientBuilder.baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ArtistReleaseAlbumResponse getArtistDetails(String mbId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path + mbId)
                        .queryParam("fmt", "json")
                        .queryParam("inc", "url-rels+release-groups")
                        .build())
                .retrieve()
                .bodyToMono(ArtistReleaseAlbumResponse.class)
                .block();
    }
}
