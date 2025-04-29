package com.practice.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class SubscriberOperator {
    public static final Logger log = LoggerFactory.getLogger(SubscriberOperator.class);
    public static void main(String[] args) {
        Flux.range(1,10)
                .doOnNext(i-> log.info("recevied {}",i))
                .doOnComplete(()->log.info("Complete"))
                .doOnError(e-> log.error("error ",e))
                .subscribe();
    }
}
