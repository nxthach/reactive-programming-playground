package com.example.sec04;

import com.example.common.Util;
import com.example.sec03.assignment.MyStockPriceObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Random;

public class Lec07FluxGenerateUtil {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    public static void main(String[] args) {

        demo2();

    }

    private static void demo1() {
        Flux.<String>generate(generator -> {
                    var country = Util.faker().country().name();
                    log.info("generated country: {}", country);
                    generator.next(country);

                    if (country.equalsIgnoreCase("canada")) {
                        generator.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }


    private static void demo2() {
        Flux.<String>generate(generator -> {
                    var country = Util.faker().country().name();
                    log.info("generated country: {}", country);
                    generator.next(country);
                })
                .takeUntil(e -> e.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }


}
