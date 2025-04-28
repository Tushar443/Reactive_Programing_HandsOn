package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxEmptyErrorDefer {
    public static void main(String[] args) {
        Flux.empty().subscribe(Util.subscriber());
        Flux.error(new RuntimeException("oops")).subscribe(Util.subscriber());
        // so delaying the Publisher creation
        Flux.defer(()-> Flux.fromIterable(List.of(1,2,3,4,5,6,7,8,9)))
                .log("flux").subscribe(Util.subscriber());
    }
}
