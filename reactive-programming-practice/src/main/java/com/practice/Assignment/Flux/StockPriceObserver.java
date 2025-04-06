package com.practice.Assignment.Flux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockPriceObserver implements Subscriber<Integer> {
    private static final Logger log = LoggerFactory.getLogger(StockPriceObserver.class);
    private int quantity = 0;
    private int balance = 1000;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
            if(price < 90 && balance >= price){
                balance-=price;
                quantity++;
                log.info("Buy a stock at {}$. total quantity: {}. Current Balance: {}",price,quantity,balance);
            }else if(price>110 && quantity>0){
                int profit = quantity * price;
                balance+=profit;
                log.info("Selling {} quantities at {}$ price and Profit is {}$",quantity,price,profit);
                quantity=0;
                subscription.cancel();
            }
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error !! {}",t);
    }

    @Override
    public void onComplete() {
        log.info("Completed!!");
    }
}
