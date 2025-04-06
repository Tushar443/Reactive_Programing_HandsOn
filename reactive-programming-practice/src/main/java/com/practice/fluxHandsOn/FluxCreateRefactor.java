package com.practice.fluxHandsOn;

import com.practice.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxCreateRefactor {
    public static void main(String[] args) {
        CountryGenerator generator = new CountryGenerator();
        Flux.create(generator).subscribe(Util.subscriber());
        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}

class CountryGenerator implements Consumer<FluxSink<String>>{

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink=stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.getFaker().country().name());
    }
}
