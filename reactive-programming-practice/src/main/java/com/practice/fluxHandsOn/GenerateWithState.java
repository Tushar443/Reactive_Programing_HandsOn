package com.practice.fluxHandsOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;


public class GenerateWithState {
    private static final Logger log = LoggerFactory.getLogger(GenerateWithState.class);

    public static void main(String[] args) {
//        generateCountryName();
//        generateOverloadedDemo();

    }

    private static void generateOverloadedDemo() {
        Flux.generate(() -> 0, (counter, sync) -> {
            String country = Util.getFaker().country().name();
            sync.next(country);
            counter++;
            if (counter == 10 || country.equalsIgnoreCase("India")) {
                sync.complete();
            }
            return counter;
        }).subscribe(Util.subscriber());
    }

    // Issue is with state we have to used external variable
    private static void generateCountryName() {
        // requirement is stop before 10 value or if country is india.
        AtomicInteger auAtomicInteger = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
            String country = Util.getFaker().country().name();
            synchronousSink.next(country);
            auAtomicInteger.incrementAndGet();
            if (auAtomicInteger.get() == 10 || country.equalsIgnoreCase("India")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
