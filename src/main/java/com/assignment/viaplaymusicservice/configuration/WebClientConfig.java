package com.assignment.viaplaymusicservice.configuration;

import io.netty.channel.ChannelOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${timeout}")
    public int timeOut;

    @Bean
    public WebClient webClientWithTimeout() {

        TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeOut);

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))
                .build();
    }
}