package com.practice.fluxMethodsHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxRange {
    public static void main(String[] args) {
//        Flux.range(1,10).subscribe(Util.subscriber());
        Flux.range(3,10).subscribe(Util.subscriber()); // 3 to 12

        //Assignment - generate 10 random first name
        Flux.range(1,10).map(i -> Util.getFaker().name().firstName()).subscribe(Util.subscriber());

    }

}
