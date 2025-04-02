package com.practice.trip_advisor.service;

import com.practice.trip_advisor.client.*;
import com.practice.trip_advisor.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class TripPlanService {

    private static final Logger log = LoggerFactory.getLogger(TripPlanService.class);
    private final EventServiceClient eventServiceClient;
    private final WeatherServiceClient weatherServiceClient;
    private final AccommodationServiceClient accommodationServiceClient;
    private final TransportationServiceClient transportationServiceClient;
    private final LocalRecommendationServiceClient localRecommendationServiceClient;
    private final ExecutorService executor;

    public TripPlan getTripPlan(String airportCode){
        Future<List<Event>> events = this.executor.submit(() -> this.eventServiceClient.getEvents(airportCode));
        Future<Weather> weather = this.executor.submit(() -> this.weatherServiceClient.getWeather(airportCode));
        Future<List<Accommodation>> accommodations = this.executor.submit(() -> this.accommodationServiceClient.getAccommodations(airportCode));
        Future<Transportation> transportation = this.executor.submit(() -> this.transportationServiceClient.getTransportation(airportCode));
        Future<LocalRecommendations> recommendations = this.executor.submit(() -> this.localRecommendationServiceClient.getRecommendations(airportCode));
        return new TripPlan(
                airportCode,
                getOrElse(accommodations, Collections.emptyList()),
                getOrElse(weather, null),
                getOrElse(events, Collections.emptyList()),
                getOrElse(recommendations, null),
                getOrElse(transportation, null)
        );
    }

    private <T> T getOrElse(Future<T> future, T defaultValue){
        try {
            return future.get();
        } catch (Exception e) {
            log.error("error", e);
        }
        return defaultValue;
    }

}