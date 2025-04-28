package com.practice.monoHandOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromSupplier {
    private static final Logger log = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        // even if we didn't subscribe it is finding the sum it is not lazy
        //Mono.just(sum(list)).subscribe(Util.subscriber());
        // Now it is lazy
        Mono.fromSupplier(()-> sum(list)).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list){
        log.info("Finding the Sum of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
