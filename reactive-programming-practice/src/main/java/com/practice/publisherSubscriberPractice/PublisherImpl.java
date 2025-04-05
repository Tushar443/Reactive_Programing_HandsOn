package com.practice.publisherSubscriberPractice;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublisherImpl implements Publisher<String> {
    private static final Logger log = LoggerFactory.getLogger(PublisherImpl.class);

    @Override
    public void subscribe(Subscriber<? super String> subscribe) {
        SubscriptionImpl subscription = new SubscriptionImpl(subscribe);
        subscribe.onSubscribe(subscription);
    }
}
