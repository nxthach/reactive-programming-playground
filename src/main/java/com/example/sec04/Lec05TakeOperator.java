package com.example.sec04;

import com.example.common.Util;
import com.example.sec01.subscriber.SubscriberImpl;
import com.example.sec03.assignment.MyStockPriceObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    public static void main(String[] args) {

        takeUntil();

    }

    private static void take() {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile() {
        Flux.range(1, 10)
                .log("take")
                .takeWhile( i -> i < 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUntil() {
        Flux.range(1, 10)
                .log("take")
                .takeUntil( i -> i > 5)
                .log("sub")
                .subscribe(Util.subscriber());
    }


}
