package com.maersk.booking.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;
import io.github.resilience4j.retry.annotation.Retry;
import reactor.core.publisher.Mono;
import com.maersk.booking.dto.CheckAvailabilityRequest;  // Assuming CheckAvailabilityRequest is in the dto package
import org.springframework.core.ParameterizedTypeReference;
import java.util.Map;





@Service
public class AvailabilityService {
	
    private final WebClient webClient;
    
    public AvailabilityService(WebClient.Builder builder, @Value("${external.api.check-available}") String url) {
        this.webClient = builder.baseUrl(url).build();
    }
    
    
    @Retry(name = "externalApi")
    public Mono<Integer> checkAvailable(CheckAvailabilityRequest request) {
        // Ensure inputs are within limits to avoid large headers
        if (request.origin().length() > 20 || request.destination().length() > 20) {
            return Mono.just(0);  // Invalid, return 0
        }
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("containerSize", request.containerSize())
                .queryParam("containerType", request.containerType().name())
                .queryParam("origin", request.origin())
                .queryParam("destination", request.destination())
                .queryParam("quantity", request.quantity())
                .build())
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<Map<String, Integer>>() {})
            .map(response -> response.getOrDefault("availableSpace", 0))
            .onErrorReturn(0);  // Return 0 on external failure
    }
}