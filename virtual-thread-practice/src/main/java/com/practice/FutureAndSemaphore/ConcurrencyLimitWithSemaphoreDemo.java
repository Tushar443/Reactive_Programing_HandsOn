package com.practice.FutureAndSemaphore;


import com.practice.FutureAndSemaphore.concurrencylimit.ConcurrencyLimiter;
import com.practice.FutureAndSemaphore.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

public class ConcurrencyLimitWithSemaphoreDemo {

    private static final Logger log = LoggerFactory.getLogger(ConcurrencyLimitWithSemaphoreDemo.class);

    public static void main(String[] args) throws Exception {
        var factory = Thread.ofVirtual().name("vins", 1).factory();
        var limiter = new ConcurrencyLimiter(Executors.newThreadPerTaskExecutor(factory), 3);
        execute(limiter, 200);
    }

    private static void execute(ConcurrencyLimiter concurrencyLimiter, int taskCount) throws Exception {
        try(concurrencyLimiter){
            for (int i = 1; i <= taskCount; i++) {
                int j = i;
                concurrencyLimiter.submit(() -> printProductInfo(j));
            }
            log.info("submitted");
        }
    }

    // 3rd party service
    // contract: 3 concurrent calls are allowed
    private static String printProductInfo(int id){
        var product = Client.getProduct(id);
        log.info("{} => {}", id, product);
        return product;
    }
}
