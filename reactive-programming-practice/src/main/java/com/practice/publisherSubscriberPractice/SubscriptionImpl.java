package com.practice.publisherSubscriberPractice;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Subscriber<? super String> subscriber;
    private static final int MAX_ITEMS=10;
    private boolean isCanceled;
    private final Faker faker;
    private int count = 0;
    public SubscriptionImpl(Subscriber<? super String> subscribe) {
        this.subscriber = subscribe;
        this.faker=Faker.instance();
    }

    @Override
    public void request(long request) {
        if(isCanceled){
            return;
        }else{
            log.info("Subscriber has requested {} items",request);
            if(request > MAX_ITEMS){
                this.subscriber.onError(new RuntimeException("Validation failed"));
                this.isCanceled=true;
                return;
            }
            for(int i = 0 ;i< request && count < MAX_ITEMS;i++){
               count++;
               this.subscriber.onNext(faker.internet().emailAddress());
            }
            if (count == MAX_ITEMS){
                log.info("No more data to produce");
                this.subscriber.onComplete();
                this.isCanceled=true;
            }
        }
    }

    @Override
    public void cancel() {
        log.info("Subscriber has cancelled");
        this.isCanceled = true;
    }
}
