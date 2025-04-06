package com.practice.fluxHandsOn;

import com.practice.common.Util;
import com.practice.publisherSubscriberPractice.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class FluxCreateDownstreamDemand {
    private static final Logger log = LoggerFactory.getLogger(FluxCreateDownstreamDemand.class);
    public static void main(String[] args) {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i <10 ; i++) {
                String name = Util.getFaker().name().firstName();
                log.info("generated: {}",name);
                fluxSink.next(name);
            }
        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
