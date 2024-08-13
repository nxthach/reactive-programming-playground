package com.example.sec06;

import com.example.common.Util;
import com.example.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Hot - 1 data producer for all the subscribers.
    share => publish().refCount(1)
    It needs 1 min subscriber to emit data.
    It stops when there is 0 subscriber.
    Re-subscription - It starts again where there is a new subscriber.
    To have min 2 subscribers, use publish().refCount(2);
 */
public class Lec02HotPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        var movieStream = movieStream().share();

        Util.sleepSeconds(2);
        movieStream
                .take(5)
                .subscribe(Util.subscriber("SAM"));

        Util.sleepSeconds(3);
        movieStream
                .take(6)
                .subscribe(Util.subscriber("MIKE"));

        Util.sleepSeconds(15); //main thread wait for other thread print out

    }


    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("received the request");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene " + state;
                            log.info("===>playing {}", scene);
                            sink.next(scene);

                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }


}
