package com.practice.operators;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class DefaultIfEmpty {
    public static void main(String[] args) {
//        Optional.empty().orElse()

//        Mono.just("")
//                .defaultIfEmpty("fallback")
//                .subscribe(Util.subscriber());
        // setting default value
        Flux.range(1,10)
                .filter(i-> i>11)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());
    }
}
