package com.assignment.viaplaymusicservice.client;

import com.assignment.viaplaymusicservice.dto.CoverArtResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CoverArtClient {

    private final WebClient webClient;
    private final String path;

    public CoverArtClient(WebClient.Builder webClientBuilder, @Value("${cover-art.base-url}") String baseUrl,
                          @Value("${cover-art.path}") String path) {

        this.path = path;
        this.webClient = webClientBuilder.baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public CoverArtResponse getCoverArtResponse(String coverArtId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path + coverArtId)
                        .build())
                .retrieve()
                .bodyToMono(CoverArtResponse.class)
                .doOnError((throwable) -> {
                    throw new RuntimeException("req failed", throwable);
                })
                .block();
    }
}


