package com.practice.CpuIntensiveTask;

import com.practice.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class CpuTaskDemo {
    private static final Logger log = LoggerFactory.getLogger(CpuTaskDemo.class);
//    private static final int TASK_COUNT = 5;
    private static final int TASK_COUNT = 3 * Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) throws InterruptedException {
//       demoCpu(Thread.ofPlatform());
       log.info("Task count : {}",TASK_COUNT);
       for(int i=0;i<3;i++){
           var totalTimeTaken = CommonUtils.timer(()->demoCpu(Thread.ofVirtual()));
           log.info("total time taken by virtual thread {} ms",totalTimeTaken);
           var totalTimeTaken2 = CommonUtils.timer(()->demoCpu(Thread.ofPlatform()));
           log.info("total time taken by platform thread {} ms",totalTimeTaken2);
       }
    }

    public static void demoCpu(Thread.Builder builder) {
        CountDownLatch countDownLatch = new CountDownLatch(TASK_COUNT);
        for (int i = 0 ;i<TASK_COUNT;i++){
            builder.start(()->{
                fibonacciSeriesTask.cpuIntensiveTask(45);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
