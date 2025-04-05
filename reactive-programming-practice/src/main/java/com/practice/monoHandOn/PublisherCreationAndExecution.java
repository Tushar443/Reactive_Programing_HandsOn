package com.practice.monoHandOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class PublisherCreationAndExecution {
    private static final Logger log = LoggerFactory.getLogger(PublisherCreationAndExecution.class);

    public static void main(String[] args) {
        // creating publisher is lightweight operation
        // Executing time-consuming business logic can be delayed
        getName(); // delay the execution not the creation of publisher
        getName().subscribe(Util.subscriber());
    }

    private static Mono<String> getName(){
        log.info("Enter into Method getName()");
        return Mono.fromSupplier(()->{
            log.info("generating name");
            Util.sleepSeconds(3);
            return Util.getFaker().name().firstName();
        });
    }
}
