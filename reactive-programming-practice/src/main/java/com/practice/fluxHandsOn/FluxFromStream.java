package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class FluxFromStream {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6);
        Stream<Integer> stream = list.stream();
        Flux<Integer> flux = Flux.fromStream(stream);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2")); //Error stream has already been operated upon or closed

        //To solve this create supplier of the streams
        Stream<Integer> stream2 = list.stream();
        Flux<Integer> flux2 = Flux.fromStream(list::stream);
        flux2.subscribe(Util.subscriber("sub1"));
        flux2.subscribe(Util.subscriber("sub2"));
    }
}
