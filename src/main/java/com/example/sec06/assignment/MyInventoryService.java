package com.example.sec06.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MyInventoryService implements Subscriber<Order> {

    private static final Logger log = LoggerFactory.getLogger(MyInventoryService.class);
    private final Map<String, Integer> quantities = new HashMap<>();


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Order order) {
        log.info("received: {}", order);

        //update quantity
        updateQuantities(order.category(), order.quantity());
    }

    @Override
    public void onError(Throwable t) {
        log.error("error", t);
    }

    @Override
    public void onComplete() {
        log.info("completed.");

    }

    public void updateQuantities(String category, int quantity) {
        var currentQuantities = quantities.getOrDefault(category, 1000);
        quantities.put(category, currentQuantities - quantity);

        log.info("current quantities: {}", quantities);

    }
}
