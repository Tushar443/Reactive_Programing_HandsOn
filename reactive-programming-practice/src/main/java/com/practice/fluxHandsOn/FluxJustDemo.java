package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class FluxJustDemo {
    public static void main(String[] args) {
//        Flux.just(1,2,3,4,"thu","nder","demo").subscribe(Util.subscriber());
       // here it is mutable
        Flux.just(1,2,3,4)
                .map(i-> i * 10)
                .subscribe(Util.subscriber());

        // Flux is immutable
        Flux<Integer> flux = Flux.range(1,10);
        flux.map(i -> i * 10);
        flux.subscribe(System.out::println);

    }
}
