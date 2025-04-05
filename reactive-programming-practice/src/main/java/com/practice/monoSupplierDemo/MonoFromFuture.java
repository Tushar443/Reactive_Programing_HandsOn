package com.practice.monoSupplierDemo;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFuture {
    private static final Logger log = LoggerFactory.getLogger(MonoFromFuture.class);

    public static void main(String[] args) {
        Mono.fromFuture(getName()).subscribe(Util.subscriber());
        Util.sleepSeconds(2);
    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()->{
            log.info("generating name");
            return Util.getFaker().name().firstName();
        });
    }
}
