package com.example.sec09;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Lec01StartWith {
    private static final Logger log = LoggerFactory.getLogger(Lec01StartWith.class);



    public static void main(String[] args) {

        demo3();

    }

    private static void demo1(){
        producer1()
                .startWith(-1, 0)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static void demo2(){
        producer2()
                .startWith(producer1())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

    private static void demo3(){
        producer2()
                .startWith(producer1())
                .startWith(-1, 0)
                .startWith(List.of(-2))
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
}
