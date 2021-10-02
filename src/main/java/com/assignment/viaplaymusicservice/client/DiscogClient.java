package com.assignment.viaplaymusicservice.client;

import com.assignment.viaplaymusicservice.dto.DiscogResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class DiscogClient {

    private final WebClient webClient;
    private final String scheme;
    private final String host;
    private final String path;

    public DiscogClient(WebClient webClient,
                        @Value("${discog.scheme}") String scheme,
                        @Value("${discog.host}") String host,
                        @Value("${discog.path}") String path) {
        this.webClient = webClient;
        this.scheme = scheme;
        this.host = host;
        this.path = path;
    }

    public DiscogResponse getDiscogResponse(String discogId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(scheme)
                        .host(host)
                        .path(path + discogId)
                        .build())
                .retrieve()
                .bodyToMono(DiscogResponse.class)
                .cache()
                .doOnError(error -> log.error("An error has occurred in discog client {}", error.getMessage()))
                .block();
    }
}
