package com.example.sec07;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehavior {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehavior.class);

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 3; i++) {
                log.info("generating: {}", i);
                fluxSink.next(i);

            }
            fluxSink.complete();
        });

        Runnable runnable = () -> flux.subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable);
    }
}
