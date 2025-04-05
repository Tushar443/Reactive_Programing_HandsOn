package com.practice.monoMethodsHandOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoFromRunnable {
    private static final Logger log = LoggerFactory.getLogger(MonoFromRunnable.class);

    public static void main(String[] args) {
        getProduct(1).subscribe(Util.subscriber("sub1"));
        getProduct(2).subscribe(Util.subscriber("sub2"));
        getProduct(3).subscribe(Util.subscriber("sub3"));
    }

    private static Mono<String> getProduct(int productId){
        if(productId==1){
            return Mono.fromSupplier(()-> Util.getFaker().commerce().productName());
        }
        return Mono.fromRunnable(()->notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        log.info("Notifying business on unavailable product {}",productId);
    }

}
