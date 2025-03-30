package com.practice.CompletableFuture;

import com.practice.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/*
    We can supply values asynchronously
    Factory method
    Executor
 */
public class SupplyAsync {

    private static final Logger log = LoggerFactory.getLogger(SupplyAsync.class);

    public static void main(String[] args) {
        log.info("main starts");
        var cf = slowTask();
        cf.thenAccept(v -> log.info("value={}", v));

        // log.info("value={}", cf.join());
        log.info("main ends");

        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> slowTask(){
        log.info("method starts");
        var cf = CompletableFuture.supplyAsync(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("method ends");
        return cf;
    }

}
