package com.practice.SyncAndLock;

import com.practice.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/*
    Virtual Threads are indented for I/O tasks. This is a simple demo to show that race conditions are still applicable. How we normally fix it.
 */
public class Lec02Synchronization {

    private static final Logger log = LoggerFactory.getLogger(Lec02Synchronization.class);
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

//        demo(Thread.ofPlatform());
        demo(Thread.ofVirtual());

        CommonUtils.sleep(Duration.ofSeconds(2));

        log.info("list size: {}", list.size());
    }

    private static void demo(Thread.Builder builder){
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                log.info("Task ended. {}", Thread.currentThread());
            });
        }
    }

    private static synchronized void inMemoryTask(){
        list.add(1);
    }

}
