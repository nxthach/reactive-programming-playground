package com.example.sec10;

import com.example.common.Util;
import com.example.sec09.Lec15Then;
import com.example.sec10.assignment.window.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec05GroupBy {

    private static final Logger log = LoggerFactory.getLogger(Lec05GroupBy.class);

    public static void main(String[] args) {
		Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(i -> i%2)
                .flatMap(Lec05GroupBy::processEvents)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> flux){
        log.info("received flux for: {}", flux.key());
        return flux.doOnNext(i -> log.info("key: {}, item: {}", flux.key(), i))
                .then();

    }


}
