package com.example.sec04;

import com.example.common.Util;
import com.example.sec03.assignment.MyStockPriceObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Random;

public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    public static void main(String[] args) {

        generateNames();

    }

    private static void generateNumber() {
        Flux.generate(generator -> {
                    generator.next(new Random().nextInt(100));
                })
                .take(10)
                .subscribe(Util.subscriber());
    }

    private static void generateNames() {
        Flux.<String>generate(generator -> {
                    var country = Util.faker().country().name();
                    log.info("generated country: {}", country);
                    generator.next(country);
                })
                .takeUntil(e -> e.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }


}
