package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

public class TakeOperator {
    public static void main(String[] args) {
//        InStreamMethod();
//        takeOperator();
//        takeWhileOperator();
        takeUntilOperator();
    }

    private static void takeUntilOperator() {
        Flux.range(1,10)
                .log("Take")
                .takeUntil(integer -> integer > 5) // stop when the condition is met + allow the last item                .log("Sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhileOperator() {
        Flux.range(1,10)
                .log("Take")
                .takeWhile(i-> i <5) // stop when the condition is not met
                .log("Sub")
                .subscribe(Util.subscriber());
    }

    private static void InStreamMethod() {
        // This is Limit for Stream
        IntStream.rangeClosed(1,10).limit(3).forEach(System.out::println);
    }

    private static void takeOperator() {
        // Same as limit we have take in Flux
        Flux.range(1,10)
                .log("Take")
                .take(3)
                .log("Sub")
                .subscribe(Util.subscriber());
    }
}
