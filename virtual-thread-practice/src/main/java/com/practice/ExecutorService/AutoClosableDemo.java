package com.practice.ExecutorService;

import com.practice.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AutoClosableDemo {
    private static final Logger log = LoggerFactory.getLogger(AutoClosableDemo.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(AutoClosableDemo::task);
        log.info("submitted");
        executorService.shutdown();

    }

    private static void task(){
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("Task executed");
    }
}
