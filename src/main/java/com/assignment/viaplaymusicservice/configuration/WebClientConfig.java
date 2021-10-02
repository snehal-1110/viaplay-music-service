package com.assignment.viaplaymusicservice.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class WebClientConfig {
    @Value("${timeout}")
    public int timeOut;

    @Bean
    public WebClient webClientWithTimeout() {
       TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeOut)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(timeOut, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(timeOut, TimeUnit.MILLISECONDS));
                });
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect((req, res) -> {
                            log.debug(res.responseHeaders().get("Location"));
                            return HttpResponseStatus.FOUND.equals(res.status());
                        }).responseTimeout(Duration.ofMillis(timeOut))
                ))
                .build();
    }
}

