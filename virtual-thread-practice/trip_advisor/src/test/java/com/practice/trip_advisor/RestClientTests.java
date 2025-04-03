package com.practice.trip_advisor;

import com.practice.trip_advisor.dto.Accommodation;
import com.practice.trip_advisor.dto.FlightReservationRequest;
import com.practice.trip_advisor.dto.FlightReservationResponse;
import com.practice.trip_advisor.dto.Weather;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;


public class RestClientTests {
    private static final Logger log = LoggerFactory.getLogger(RestClientTests.class);
    @Test
    void simpleTest(){
        RestClient client = RestClient.create();
        Weather body = client.get()
                .uri("http://localhost:7070/sec02/weather/LAS")
                .retrieve()
                .body(Weather.class);

        log.info("response {}",body);
    }
    @Test
    void BaseUrlDemo(){
        RestClient client = RestClient.builder().baseUrl("http://localhost:7070/sec02/weather/").build();
        Weather body = client.get()
                .uri("LAS")
                .retrieve()
                .body(Weather.class);
        log.info("response {}",body);
    }
    @Test
    void BaseUrlWithParameter(){
        RestClient client = RestClient.builder().baseUrl("http://localhost:7070/sec02/weather/").build();
        Weather body = client.get()
                .uri("{airportCode}","LAS")
                .retrieve()
                .body(Weather.class);
        log.info("response {}",body);
    }

    @Test
    void listResponse(){
        RestClient client = RestClient.builder().baseUrl("http://localhost:7070/sec02/accommodations/").build();
        List<Accommodation> res = client.get()
                .uri("{airportCode}","LAS")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Accommodation>>() {
                    @Override
                    public Type getType() {
                        return super.getType();
                    }
                });
        log.info("response {}",res);
    }

    @Test
    void postRequest(){
        RestClient client = RestClient.builder().baseUrl("http://localhost:7070/sec03/flight/reserve/").build();
        FlightReservationRequest flightReservationRequest = new FlightReservationRequest("ATL", "LAS", "UA789", LocalDate.now());
        FlightReservationResponse res = client
                .post()
                .body(flightReservationRequest) // .header("token","value") add info
                .retrieve()
                .body(FlightReservationResponse.class);
        log.info("response {}",res);
    }
}
