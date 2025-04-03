package com.practice.trip_advisor.controller;

import com.practice.trip_advisor.dto.FlightReservationResponse;
import com.practice.trip_advisor.dto.TripPlan;
import com.practice.trip_advisor.dto.TripReservationRequest;
import com.practice.trip_advisor.service.TripPlanService;
import com.practice.trip_advisor.service.TripReservationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
public class TripController {

    private static final Logger log = LoggerFactory.getLogger(TripController.class);
    private final TripPlanService planService;
    private final TripReservationService reservationService;

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode){
//        log.info("airport code : {}, is Virtual: {} ",airportCode,Thread.currentThread());
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request){
        return this.reservationService.reserve(request);
    }

}