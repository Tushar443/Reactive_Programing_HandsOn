package com.practice.CpuIntensiveTask;

import com.practice.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class fibonacciSeriesTask {

    private static final Logger log = LoggerFactory.getLogger(fibonacciSeriesTask.class);

    public static void cpuIntensiveTask(int i ){
        //log.info("starting cpu task. Thread info : {}",Thread.currentThread());
        var timeTaken = CommonUtils.timer(()->findFib(i));
        //log.info("ending cpu task. Thread info : {}", timeTaken + " ms");
    }

    private static long findFib(long input){
        if(input < 2){
            return input;
        }else{
            return findFib(input -1 )+ findFib(input - 2);
        }
    }
}

