package com.practice.fluxMethodsHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class MultipleSubscriber {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5, 6,7,8);
        flux.subscribe(Util.subscriber("sub1"));
        flux.filter(i->i>7)
                .subscribe(Util.subscriber("sub2"));
        flux.filter(a-> a%2 == 0).map(a-> a+"a")
                .subscribe(Util.subscriber("sub3"));

    }
}
