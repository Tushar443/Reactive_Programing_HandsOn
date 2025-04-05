package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromIterableOrArray {
    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c");
        Flux.fromIterable(list).subscribe(Util.subscriber("Itr"));
        Integer[] arr = {1,2,3,4,5,6};
        Flux.fromArray(arr).subscribe(Util.subscriber("Arr"));
    }
}
