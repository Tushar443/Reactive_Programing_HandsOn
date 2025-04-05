package com.practice.httpClientRequestDemo;

import com.practice.common.Util;
import com.practice.monoMethodsHandOn.MonoFromSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
