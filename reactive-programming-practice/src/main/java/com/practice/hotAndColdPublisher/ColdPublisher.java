package com.practice.hotAndColdPublisher;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class ColdPublisher {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Flux<Integer> flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                fluxSink.next(atomicInteger.incrementAndGet());
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }
}
