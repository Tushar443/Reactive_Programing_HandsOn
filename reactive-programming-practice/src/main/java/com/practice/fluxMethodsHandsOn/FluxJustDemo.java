package com.practice.fluxMethodsHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class FluxJustDemo {
    public static void main(String[] args) {
        Flux.just(1,2,3,4,"thu","nder","demo").subscribe(Util.subscriber());
    }
}
