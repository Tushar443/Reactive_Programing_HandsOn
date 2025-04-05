package com.practice.monoMethodsHandOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromCallable {
    private static final Logger log = LoggerFactory.getLogger(MonoFromCallable.class);

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        Mono.fromCallable(()-> sum(list)).subscribe(Util.subscriber());   // NO error
//        Mono.fromSupplier(()-> sum(list)).subscribe(Util.subscriber()); // throwing error
    }

    private static int sum(List<Integer> list) throws Exception{
        log.info("Finding the Sum of {}",list);
        return list.stream().mapToInt(a->a).sum();
    }
}
