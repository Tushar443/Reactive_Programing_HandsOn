package com.practice.operators;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class TransformOperator {
    public static final Logger log = LoggerFactory.getLogger(TransformOperator.class);

    public static void main(String[] args) {
        /*
            Old Code repeat code
         */
        /*
        getCustomer()
                .doOnNext(i -> log.info("recevied {}", i))
                .doOnComplete(() -> log.info("Complete"))
                .doOnError(e -> log.error("error ", e))
                .subscribe();
        getPurchaseOrders()
                .doOnNext(i -> log.info("recevied {}", i))
                .doOnComplete(() -> log.info("Complete"))
                .doOnError(e -> log.error("error ", e))
                .subscribe();

         */

        /*
            New code with Transform
         */
        boolean isDebugEnabled = true;
        getCustomer()
                .transform(isDebugEnabled ? addDebugger() : Function.identity())
                .subscribe();
        getPurchaseOrders()
                .transform(isDebugEnabled ? addDebugger() : Function.identity())
                .subscribe();
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(i -> log.info("recevied {}", i))
                .doOnComplete(() -> log.info("Complete"))
                .doOnError(e -> log.error("error ", e));
    }

    public static Flux<Customer> getCustomer() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.getFaker().name().firstName()));
    }

    public static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 3)
                .map(i -> new PurchaseOrder(Util.getFaker().commerce().productName(), i, i * 10));
    }

    record Customer(int id, String name) {
    }

    record PurchaseOrder(String productNme, int price, int quantity) {
    }
}
