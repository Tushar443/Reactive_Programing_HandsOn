package com.practice.operators;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.io.Flushable;


public class HandleOperator {
    public static void main(String[] args) {
        //handleDemo();
        handleAssignment();
    }

    private static void handleDemo() {
        // using normal filter and map
        Flux.range(1, 10)
                .map(i -> {
                    if (i == 1) {
                        return -2;
                    }
                    return i;
                })
                .filter(i -> i != 4)
                .map(i -> {
                    if (i == 7) {
                        return "error";
                    }
                    return i;
                })
                .subscribe(Util.subscriber());

        // now we used Handle
        System.out.println("--------------- Handle Operator -------------");
        Flux.range(1, 10).handle((item, sink) -> {
            switch (item) {
                case 1 -> sink.next(-2);
                case 4 -> {
                }
                case 7 -> sink.next("Oops error");
                default -> sink.next(item);
            }
        }).subscribe(Util.subscriber());
    }

    public static void handleAssignment() {
        // using takeUntil operator
        Flux.<String>generate(sink -> {
                    String country = Util.getFaker().country().name();
                    sink.next(country);
                })
                .takeUntil(c -> c.equalsIgnoreCase("India"))
                .subscribe(Util.subscriber());

        // using Handle Operator
        System.out.println("################################ Handle ############");

        Flux.<String>generate(sink -> sink.next(Util.getFaker().country().name()))
                .handle((country, sink) -> {
                    sink.next(country);
                    if (country.equalsIgnoreCase("India"))
                        sink.complete();
                }).subscribe(Util.subscriber());
    }
}