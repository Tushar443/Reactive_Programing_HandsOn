package com.practice.fluxHandsOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FluxSinkThreadSafety {
    private static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafety.class);

    public static void main(String[] args) {
//        addList();
        addListUsingFlux();
    }

    private static void addListUsingFlux() {
        List<String> list = new ArrayList<>();
        CountryGeneratorThread generator = new CountryGeneratorThread();
        Flux.create(generator).subscribe(list::add);
        Runnable runnable = ()->{
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
//            new Thread(runnable).start();
            Thread.ofPlatform().start(runnable);
        }

        Util.sleepSeconds(3);
        log.info("list size {}",list.size());
    }

    private static void addList(){
        List<Integer> list = new ArrayList<>();
        Runnable runnable = ()->{
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
//            new Thread(runnable).start();
            Thread.ofPlatform().start(runnable);
        }

        Util.sleepSeconds(3);
        log.info("list size {}",list.size());
    }
}

class CountryGeneratorThread implements Consumer<FluxSink<String>> {

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink=stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.getFaker().country().name());
    }
}