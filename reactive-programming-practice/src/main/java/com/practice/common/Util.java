package com.practice.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Util {
    private static final Faker faker = Faker.instance();
    public static <T>Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }

    public static <T>Subscriber<T> subscriber(String name){
        return new DefaultSubscriber<>(name);
    }

    public static Faker getFaker() {
        return faker;
    }

    public static void sleepSeconds(int sec){
        try {
            Thread.sleep(Duration.ofSeconds(sec));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Mono.just(1).subscribe(subscriber("thunder1"));
        Mono.just(1).subscribe(subscriber("thunder2"));
        Mono.just(1).subscribe(subscriber("thunder3"));
    }
}
