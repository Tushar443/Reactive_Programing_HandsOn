package com.practice.trip_advisor.service;

import com.practice.trip_advisor.client.FlightReservationServiceClient;
import com.practice.trip_advisor.client.FlightSearchServiceClient;
import com.practice.trip_advisor.dto.Flight;
import com.practice.trip_advisor.dto.FlightReservationRequest;
import com.practice.trip_advisor.dto.FlightReservationResponse;
import com.practice.trip_advisor.dto.TripReservationRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripReservationService {
    private static final Logger log = LoggerFactory.getLogger(TripReservationService.class);
    private final FlightSearchServiceClient searchServiceClient;
    private final FlightReservationServiceClient reservationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest request){
        List<Flight> flights = this.searchServiceClient.getFlights(request.departure(), request.arrival());
        Optional<Flight> bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));
        Flight flight = bestDeal.orElseThrow(() -> new IllegalStateException("no flights found"));
        FlightReservationRequest reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(), request.date());
        return this.reservationServiceClient.reserve(reservationRequest);
    }

}
