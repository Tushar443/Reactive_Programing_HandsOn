package com.practice.monoMethodsHandOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromSupplier {
    private static final Logger log = LoggerFactory.getLogger(MonoFromSupplier.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        //Mono.just(sum(list)); // even if we didn't subscribe it is finding the sum it is not lazy
                //.subscribe(Util.subscriber());
        Mono.fromSupplier(()-> sum(list)).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list){
        log.info("Finding the Sum of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
