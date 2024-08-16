package com.example.sec09;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec04ConcatError {
    private static final Logger log = LoggerFactory.getLogger(Lec04ConcatError.class);


    public static void main(String[] args) {

        demo2();

    }

    private static void demo1() {
        producer1()
                .concatWith(producerError())
                .concatWith(producer2()) //not reach this producer
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static void demo2() {
        Flux.concatDelayError(producer1(), producerError(), producer2())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(i -> log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(4, 5, 6)
                .doOnSubscribe(i -> log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producerError() {
        return Flux.error(new RuntimeException("oops"));
    }
}
