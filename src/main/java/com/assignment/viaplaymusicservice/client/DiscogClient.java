package com.assignment.viaplaymusicservice.client;

import com.assignment.viaplaymusicservice.dto.DiscogResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DiscogClient {

    private final WebClient webClient;
    private final String path;

    public DiscogClient(WebClient.Builder webClientBuilder, @Value("${discog.base-url}") String baseUrl,
                        @Value("${discog.path}") String path) {

        this.path = path;
        this.webClient = webClientBuilder.baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public DiscogResponse getDiscogResponse(String discogId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path + discogId)
                        .build())
                .retrieve()
                .bodyToMono(DiscogResponse.class)
                .doOnError((throwable) -> {
                    throw new RuntimeException("req failed", throwable);
                })
                .block();
    }
}
