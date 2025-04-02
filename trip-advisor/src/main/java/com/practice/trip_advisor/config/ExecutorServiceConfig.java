package com.practice.trip_advisor.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnThreading;
import org.springframework.boot.autoconfigure.thread.Threading;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorServiceConfig {

    @Bean
    @ConditionalOnThreading(Threading.VIRTUAL)
    public ExecutorService virtualThreadExecutor(){
        return Executors.newCachedThreadPool();
    }

    @Bean
    @ConditionalOnThreading(Threading.PLATFORM)
    public ExecutorService platformThreadExecutor(){
        return Executors.newCachedThreadPool();
    }

}
