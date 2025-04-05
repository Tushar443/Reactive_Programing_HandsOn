package com.practice.monoDemo;

import com.practice.publisherSubscriberPractice.SubscriberImpl;
import reactor.core.publisher.Mono;

public class MonoJustDemo {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("thunder");
        System.out.println("Hello mono : "+ mono);
        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(10);
        // no effect as publisher already sent complete
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }
}
