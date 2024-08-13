package com.example.sec06;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    - publish().autoConnect(0) will provide new values to the subscribers.
    - replay allows us to cache
 */
public class Lec04HotPublisherCache {
    private static final Logger log = LoggerFactory.getLogger(Lec04HotPublisherCache.class);

    public static void main(String[] args) {
        var stockStream = stockStream().replay().autoConnect(0);

        Util.sleepSeconds(4);
        log.info("SAM joining");
        stockStream
                .subscribe(Util.subscriber("SAM"));

        Util.sleepSeconds(4);
        log.info("MIKE joining");
        stockStream
                .subscribe(Util.subscriber("MIKE"));

        Util.sleepSeconds(15); //main thread wait for other thread print out

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }


}
