package com.maersk.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        HttpClient httpClient = HttpClient.create()
                .wiretap(true)
                .compress(true)
                .httpResponseDecoder(spec ->
                        spec.maxHeaderSize(16 * 1024)  // 16 KB
                            .maxInitialLineLength(4096));

        return builder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}