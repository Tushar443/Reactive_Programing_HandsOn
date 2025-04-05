package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class FluxLogOperator {
    public static void main(String[] args) {
//        Flux.range(1,5)
//                .log("operator")
//                .subscribe(Util.subscriber());
        // emmit the name
        Flux.range(1,5)
                .log("range - map")
                .map(i-> Util.getFaker().name().firstName())
                .log("map - subscriber")
                .subscribe(Util.subscriber());
    }
}
