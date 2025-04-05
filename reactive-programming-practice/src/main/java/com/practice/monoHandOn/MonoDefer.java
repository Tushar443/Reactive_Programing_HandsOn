package com.practice.monoHandOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoDefer {
    private static final Logger log = LoggerFactory.getLogger(MonoDefer.class);

    public static void main(String[] args) {
//        createPublisher().subscribe(Util.subscriber());
        //createPublisher(); // creating publisher is time consuming
//        //.subscribe(Util.subscriber());
        // so delaying the Publisher creation
        Mono.defer(MonoDefer::createPublisher).subscribe(Util.subscriber());
        // it will create the publisher only when we have the subscriber
    }

    private static Mono<Integer> createPublisher(){
        log.info("creating publisher");
        List<Integer> list = List.of(1, 2, 3);
        Util.sleepSeconds(3);
        return Mono.fromSupplier(()-> sum(list));
    }

    // time consuming business logic
    private static int sum(List<Integer> list){
        log.info("Finding the Sum of {}",list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a->a).sum();
    }
}
