package com.practice.operators;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class SwitchIfEmpty {
    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(i-> i>11)
                .switchIfEmpty(fallBack())
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> fallBack(){
        return Flux.range(100,3);
    }
}
