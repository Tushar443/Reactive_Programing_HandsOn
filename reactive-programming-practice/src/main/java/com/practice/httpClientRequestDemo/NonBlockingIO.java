package com.practice.httpClientRequestDemo;

import com.practice.common.AbstractHttpClient;
import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(NonBlockingIO.class);

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        log.info("starting");
        for(int i=0;i<100;i++){
//            client.getProductName(i).subscribe(Util.subscriber("sub= "+i));
            String name = client.getProductName(i).block();
            log.info("Name = {}",name);

        }
        Util.sleepSeconds(10);
    }

}
class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
                .uri("/demo01/product/"+productId)
                .responseContent()
                .asString()
                .next();
    }

}
