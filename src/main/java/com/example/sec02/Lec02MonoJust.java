package com.example.sec02;

import com.example.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    private static final Logger log = LoggerFactory.getLogger(Lec02MonoJust.class);

    public static void main(String[] args) {
        var mono = Mono.just("Tony");
        var subscriber = new SubscriberImpl();

        //
        mono.subscribe(subscriber);

        //
        subscriber.getSubscription().request(3);
    }
}
