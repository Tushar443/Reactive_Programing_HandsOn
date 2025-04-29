package com.practice.operators;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class ErrorHandling {
    public static void main(String[] args) {
//        errorHandling();
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .subscribe(Util.subscriber());
    }

    private static void errorHandling() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }
}
