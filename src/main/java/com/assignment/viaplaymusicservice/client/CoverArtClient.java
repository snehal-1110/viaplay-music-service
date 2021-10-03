package com.assignment.viaplaymusicservice.client;

import com.assignment.viaplaymusicservice.dto.CoverArtResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@Slf4j
public class CoverArtClient {

    private final WebClient webClient;
    private final String scheme;

    private final String host;
    private final String path;

    public CoverArtClient(WebClient webClient,
                          @Value("${cover-art.scheme}") String scheme,
                          @Value("${cover-art.host}") String host, @Value("${cover-art.path}") String path) {
        this.webClient = webClient;
        this.scheme = scheme;
        this.host = host;
        this.path = path;
    }

    public CoverArtResponse getCoverArtResponse(String coverArtId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(scheme)
                        .host(host)
                        .path(path + coverArtId)
                        .build())
                .exchangeToMono(response -> {
                    if (response.statusCode().is3xxRedirection()) {
                        String redirectUrl = response.headers().header("Location").get(0);
                        return response.bodyToMono(Void.class).then(followRedirect(redirectUrl));
                    }
                    return response.bodyToMono(CoverArtResponse.class);
                })
                .retryWhen(Retry.fixedDelay(3, Duration.ofMillis(10)))
                .doOnError(error -> log.error("An error has occurred in cover art client {}", error.getMessage()))
                .block();

    }

    public Mono<CoverArtResponse> followRedirect(String redirectUrl) {
        return webClient.get()
                .uri(redirectUrl)
                .exchangeToMono(
                        response -> {
                            return response.bodyToMono(CoverArtResponse.class);
                        })
                .doOnError(error -> log.error("An error has occurred in cover art client {}", error.getMessage()));
    }

}
