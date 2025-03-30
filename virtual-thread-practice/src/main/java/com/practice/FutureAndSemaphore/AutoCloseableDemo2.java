package com.practice.FutureAndSemaphore;

import com.practice.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;

/*
    ExecutorService now extends the AutoCloseable
*/
public class AutoCloseableDemo2 {

    private static final Logger log = LoggerFactory.getLogger(AutoCloseableDemo2.class);

    public static void main(String[] args) {

    }

    // w/o autocloseable - we have to issue shutdown for short-lived applications
    private static void withoutAutoCloseable(){
        var executorService = Executors.newSingleThreadExecutor();
        executorService.submit(AutoCloseableDemo2::task);
        log.info("submitted");
        executorService.shutdown();
    }

    private static void withAutoCloseable(){
        try(var executorService = Executors.newSingleThreadExecutor()){
            executorService.submit(AutoCloseableDemo2::task);
            executorService.submit(AutoCloseableDemo2::task);
            executorService.submit(AutoCloseableDemo2::task);
            executorService.submit(AutoCloseableDemo2::task);
            log.info("submitted");
        }
    }

    private static void task(){
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("task executed");
    }

}
