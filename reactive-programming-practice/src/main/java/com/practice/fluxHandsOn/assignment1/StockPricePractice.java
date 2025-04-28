package com.practice.fluxHandsOn.assignment1;

import com.practice.common.AbstractHttpClient;
import com.practice.common.Util;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class StockPricePractice {
    private static final Logger log = LoggerFactory.getLogger(StockPricePractice.class);

    public static void main(String[] args) {
        log.info("started");
        ExternalServiceClient client = new ExternalServiceClient();
        Subscriber<Integer> subscriber = new StockPriceObserver();
        client.getStockPrice().subscribe(subscriber);
        Util.sleepSeconds(20);
        log.info("ended");
    }
}

class ExternalServiceClient extends AbstractHttpClient {

    public Flux<Integer> getStockPrice(){
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
