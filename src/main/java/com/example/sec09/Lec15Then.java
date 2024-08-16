package com.example.sec09;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;


public class Lec15Then {

    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {

        var records = List.of("A", "B", "C");

        saveRecords(records)
                .doOnComplete(() -> log.info("completed!"))
                .then(sendNotification(records))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "save" + r)
                .delayElements(Duration.ofMillis(500));


    }

    private static Mono<Void> sendNotification(List<String> records) {
        return Mono.fromRunnable(() -> log.info("all these {} records saved successfully", records));
    }
}
