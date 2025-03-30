package com.practice.CompletableFuture;

import com.practice.CompletableFuture.aggregator.AggregatorService;
import com.practice.CompletableFuture.aggregator.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
    Demo the use of all-of
 */
public class UsedAllOfDemo {

    private static final Logger log = LoggerFactory.getLogger(UsedAllOfDemo.class);

    public static void main(String[] args) {

        // beans / singletons
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        // create futures
        List<CompletableFuture<ProductDto>> futures = IntStream.rangeClosed(1, 100)
                               .mapToObj(id -> CompletableFuture.supplyAsync(() -> aggregator.getProductDto(id), executor))
                               .toList();

        // wait for all the completable-futures to complete
        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        var list = futures.stream()
                          .map(CompletableFuture::join)
                          .toList();

        log.info("list: {}", list);
    }


}
