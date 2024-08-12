package com.example.sec04;

import com.example.common.Util;
import com.example.sec03.assignment.MyStockPriceObserver;
import com.example.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Lec03FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    public static void main(String[] args) {

        demo2();

    }

    private static void demo1(){
        var list = new ArrayList<>();

        Runnable run = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(run);
        }

        Util.sleepSeconds(3);

        log.info("list size: {}", list.size());
    }

    private static void demo2(){
        var list = new ArrayList<>();

        var generator = new NameGenerator();
        var flux = Flux.create(generator);

        flux.subscribe(list::add);

        Runnable run = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(run);
        }

        Util.sleepSeconds(3);

        log.info("list size: {}", list.size());
    }

}
