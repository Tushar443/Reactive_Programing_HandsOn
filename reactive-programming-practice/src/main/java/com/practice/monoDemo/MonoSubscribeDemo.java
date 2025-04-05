package com.practice.monoDemo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoSubscribeDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoSubscribeDemo.class);

    public static void main(String[] args) {

        log.info("=== Only Subscriber calling ===");
        Mono.just(1).subscribe(i->log.info("received O/P: {}",i));

        log.info("=== Overload Method ===");
        Mono.just(1).subscribe(
                i->log.info("received: {}",i),
                err -> log.error("error", err),
                ()-> log.info("Completed!!!"),
                subscription -> subscription.request(10) // we are requesting 10 but received only one
        );


        log.info("=== Creating Error ===");
        Mono<Integer> mono = Mono.just(1)
                                 .map(i-> i/0);
        mono.subscribe(
                i->log.info("received: {}",i),
                err -> log.error("error", err),
                ()-> log.info("Completed!!!"),
                subscription -> subscription.request(10)
        );

    }
}
