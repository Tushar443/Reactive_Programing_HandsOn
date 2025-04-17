package com.practice.fluxHandsOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * Flux Generator
 * - invokes the given lambda expression again and again based on downstream demand
 * - We can emit only one value at a time
 * - will stop when complete method is invoke
 * - will stop when error method is invoke
 * - will stop when downstream cancels
 * -
 */

public class FluxGenerateDemo {
    private static final Logger log = LoggerFactory.getLogger(FluxGenerateDemo.class);

    public static void main(String[] args) {
//        generatedDemo();
//        generateCountryName();
        generateCountryNameUsingTakeUntil();
    }

    private static void generateCountryName() {
        Flux.generate(synchronousSink -> {
            String name = Util.getFaker().country().name();
            synchronousSink.next(name);
            log.info("emmited {}", name);
            if (name.equalsIgnoreCase("India")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void generateCountryNameUsingTakeUntil() {
        Flux.<String>generate(synchronousSink -> {
            String name = Util.getFaker().country().name();
            synchronousSink.next(name);
            log.info("emmited {}", name);
        }).takeUntil(s -> s.equalsIgnoreCase("India")).subscribe(Util.subscriber());
    }

    private static void generatedDemo() {
        Flux.generate(synchronousSink -> {
            synchronousSink.next(1);
//           synchronousSink.next(2);
//            synchronousSink.complete();
//            synchronousSink.error(new RuntimeException("oops"));
        }).take(5).subscribe(Util.subscriber());
    }
}
