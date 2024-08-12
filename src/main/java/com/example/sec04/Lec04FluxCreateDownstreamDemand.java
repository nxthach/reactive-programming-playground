package com.example.sec04;

import com.example.common.Util;
import com.example.sec01.subscriber.SubscriberImpl;
import com.example.sec03.assignment.MyStockPriceObserver;
import com.example.sec04.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Lec04FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    public static void main(String[] args) {

        produceOnDemand();

    }

    private static void produceEarly(){
        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated name: {}", name);
                fluxSink.next(name);
            }

            fluxSink.complete();

        }).subscribe(subscriber);


        subscriber.getSubscription().request(2);
        Util.sleepSeconds(2);

        subscriber.getSubscription().request(2);

        subscriber.getSubscription().cancel();

    }

    private static void produceOnDemand(){
        var subscriber = new SubscriberImpl();

        Flux.<String>create(fluxSink -> {

            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated name: {}", name);
                    fluxSink.next(name);
                }
            });

        }).subscribe(subscriber);

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        System.out.println("---");

        Util.sleepSeconds(2);
        subscriber.getSubscription().request(2);
        System.out.println("---");

        subscriber.getSubscription().cancel();
    }

}
