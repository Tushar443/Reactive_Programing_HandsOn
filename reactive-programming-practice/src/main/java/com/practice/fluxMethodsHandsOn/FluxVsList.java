package com.practice.fluxMethodsHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class FluxVsList {
    public static void main(String[] args) {
        System.out.println("started normal list");
//        List<String> nameList = NameGenerator.getNameList(10);
  //      System.out.println(nameList);
        System.out.println("started Flux list");
        NameGenerator.getNameFlux(10).subscribe(Util.subscriber());

    }

}

class NameGenerator{

    public static List<String> getNameList(int count){
        return IntStream.rangeClosed(1,count)
                .mapToObj(i->generateName())
                .toList();
    }

    public static Flux<String> getNameFlux(int count){
        return Flux.range(1,count)
                .map(i->generateName());
    }

    private static String generateName(){
        Util.sleepSeconds(1);
        return Util.getFaker().name().firstName();
    }
}
