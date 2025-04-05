package com.practice.monoSupplierDemo;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoFromRunnable {
    private static final Logger log = LoggerFactory.getLogger(MonoFromRunnable.class);

    public static void main(String[] args) {
        getProduct(2).subscribe(Util.subscriber());
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
