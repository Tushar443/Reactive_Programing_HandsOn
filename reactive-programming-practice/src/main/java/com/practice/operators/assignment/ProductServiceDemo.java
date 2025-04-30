package com.practice.operators.assignment;

import com.practice.common.AbstractHttpClient;
import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductServiceDemo {
    public static final Logger log = LoggerFactory.getLogger(ProductServiceDemo.class);

    public static void main(String[] args) {
        ExternalServiceProductId client = new ExternalServiceProductId();
//        log.info(client.getProductName(1));
        for (int i = 1; i <5 ; i++) {
            client.getProductName(i).subscribe(Util.subscriber());
        }
        Util.sleepSeconds(3);
    }
}

class ExternalServiceProductId extends AbstractHttpClient {

    public Mono<String> getProductName(int id) {
        String defaultPath = "/demo03/product/" + id;
        String fallbackPath = "/demo03/timeout-fallback/product/" + id;
        String emptyPath = "/demo03/empty-fallback/product/" + id;
        return getProductByName(defaultPath)
                .timeout(Duration.ofSeconds(2), getProductByName(fallbackPath))
                .switchIfEmpty(getProductByName(emptyPath));
    }

    public Mono<String> getProductByName(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent().asString().next();
    }


}
