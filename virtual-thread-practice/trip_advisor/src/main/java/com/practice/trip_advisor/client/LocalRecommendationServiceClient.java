package com.practice.trip_advisor.client;

import com.practice.trip_advisor.dto.LocalRecommendations;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class LocalRecommendationServiceClient {

    private final RestClient restClient;

    public LocalRecommendations getRecommendations(String airportCode) {
        return this.restClient.get()
                              .uri("{airportCode}", airportCode)
                              .retrieve()
                              .body(LocalRecommendations.class);
    }

}
