package com.practice.emptyErrorDemo;

import com.practice.common.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyError {
    public static void main(String[] args) {
        getUserName(1).subscribe(Util.subscriber("sub1"));
        getUserName(2).subscribe(Util.subscriber("sub2"));
        getUserName(3).subscribe(Util.subscriber("sub3"));
        getUserName(3).subscribe(s-> System.out.println(s)); //Operator called default onErrorDropped
    }

    private static Mono<String> getUserName(int userId){
        return switch (userId){
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty(); // same as NULL
            default -> Mono.error(new RuntimeException("Invalid Input"));
        };
    }
}
