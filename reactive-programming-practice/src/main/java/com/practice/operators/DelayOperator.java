package com.practice.operators;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayOperator {
    public static void main(String[] args) {
        Flux.range(1,10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }
}
