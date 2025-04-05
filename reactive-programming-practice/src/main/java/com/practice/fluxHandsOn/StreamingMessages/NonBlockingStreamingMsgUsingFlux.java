package com.practice.fluxHandsOn.StreamingMessages;

import com.practice.common.AbstractHttpClient;
import com.practice.common.Util;
import reactor.core.publisher.Flux;

public class NonBlockingStreamingMsgUsingFlux {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getName().subscribe(Util.subscriber("sub1"));
        client.getName().subscribe(Util.subscriber("sub2"));
        Util.sleepSeconds(6);
    }
}

class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getName(){
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }
}