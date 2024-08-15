package com.example.sec07;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscriberOn {

    private static final Logger log = LoggerFactory.getLogger(Lec02SubscriberOn.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        log.info("generating: {}", i);
                        fluxSink.next(i);

                    }
                    fluxSink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable);

        Runnable runnable2 = () -> flux.subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable2);

        Util.sleepSeconds(2);
    }
}
