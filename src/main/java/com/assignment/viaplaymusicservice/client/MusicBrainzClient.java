package com.assignment.viaplaymusicservice.client;

import com.assignment.viaplaymusicservice.dto.ArtistReleaseAlbumResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class MusicBrainzClient {

  private final WebClient webClient;
  private final String scheme;
  private final String host;
  private final String path;

  public MusicBrainzClient(WebClient webClient,
      @Value("${music-brainz.scheme}") String scheme,
      @Value("${music-brainz.host}") String host,
      @Value("${music-brainz.path}") String path) {
    this.webClient = webClient;
    this.scheme = scheme;
    this.host = host;
    this.path = path;
  }

  public ArtistReleaseAlbumResponse getArtistDetails(String mbId) {
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .scheme(scheme)
            .host(host)
            .path(path + mbId)
            .queryParam("fmt", "json")
            .queryParam("inc", "url-rels+release-groups")
            .build())
        .retrieve()
        .bodyToMono(ArtistReleaseAlbumResponse.class)
        .cache()
        .doOnError(error -> log.error("An error has occurred in music brainz client {}", error.getMessage()))
        .block();
  }
}
