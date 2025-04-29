package com.practice.operators;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ErrorHandling {
    public static final Logger log = LoggerFactory.getLogger(ErrorHandling.class);
    public static void main(String[] args) {
//        onErrorReturnDemo();
//        onErrorResumeDemo();
//        onErrorCompleteDemo();
        onErrorContinueDemo();
    }

    private static void onErrorContinueDemo() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorContinue((ex, obj) -> log.error("==> {}",obj,ex))
                .subscribe(Util.subscriber());
    }

    private static void onErrorCompleteDemo() {
        Mono.error(new RuntimeException("ooops"))
                .onErrorComplete() // if error occurs it will send complete it will skipped error
                .subscribe(Util.subscriber());
    }

    private static void onErrorResumeDemo() {
        //        Flux.range(1, 10)
        Flux.error(new RuntimeException("oops")) // It will triggered fallBack2()
//                .map(i -> i == 5 ? 5 / 0 : i) // It will triggered fallBack1()
                .onErrorResume(ArithmeticException.class, e -> fallBack1())
                .onErrorResume(e -> fallBack2())
                .onErrorReturn(-5) // it will get if fallBack2() give error
                .subscribe(Util.subscriber());
    }

    private static void onErrorReturnDemo() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }

    /**
     * Consider fallBack1 as a One Microservice
     *
     * @return
     */
    public static Mono<Integer> fallBack1() {
        return Mono.fromSupplier(() -> Util.getFaker().random().nextInt(10, 100));
    }

    /**
     * Consider fallBack1 as a Second Microservice
     *
     * @return
     */
    public static Mono<Integer> fallBack2() {
        return Mono.fromSupplier(() -> Util.getFaker().random().nextInt(100, 1000));
//        return Mono.error(new RuntimeException("ooops")); // In case if this microservice failed
    }
}
