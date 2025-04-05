package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxToMono {
    public static void main(String[] args) {
        // Mono to Flux
        Mono<String> mono = getUserName(1);
//        Mono<String> mono1 = getUserName(2);
//        Mono<String> mono2 = getUserName(3);
        save(Flux.from(mono));

        //Flux to Mono
//        Flux.range(1,10).next().subscribe(Util.subscriber("FluxToMono"));
        Flux<Integer> flux = Flux.range(1, 10);
        Mono.from(flux).subscribe(Util.subscriber("FluxToMono"));
    }
    private static Mono<String> getUserName(int userId){
        return switch (userId){
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty(); // same as NULL
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber());
    }
}
