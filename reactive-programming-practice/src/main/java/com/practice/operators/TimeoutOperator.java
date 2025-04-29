package com.practice.operators;

import com.practice.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TimeoutOperator {
    public static void main(String[] args) {
//        oneTimeoutDemo();
        multipleTimeoutDemo();
    }

    private static void multipleTimeoutDemo() {
        Mono<String> mono = getProductService().timeout(Duration.ofSeconds(1), fallBack());
        mono
                .timeout(Duration.ofMillis(5000))
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);
    }

    private static void oneTimeoutDemo() {
        getProductService()
                .timeout(Duration.ofSeconds(2), fallBack())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    public static Mono<String> getProductService() {
        return Mono.fromSupplier(() -> "getProductService = " + Util.getFaker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    public static Mono<String> fallBack() {
        return Mono.fromSupplier(() -> "fallBack = " + Util.getFaker().commerce().productName())
                .delayElement(Duration.ofMillis(300));
    }
}
