package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            // only emmit 8 values
//            for (int i = 0; i < 10; i++) {
//                fluxSink.next(i);
//                if(i==8)
//                    fluxSink.complete();
//            }
                // stop until the country is India
                String country;
                do {
                    country = Util.getFaker().country().name();
                    fluxSink.next(country);
                }while (!country.equalsIgnoreCase("India"));
                fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
