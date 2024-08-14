package com.example.sec06.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MyRevenueService implements Subscriber<Order> {

    private static final Logger log = LoggerFactory.getLogger(MyRevenueService.class);
    private final Map<String, Integer> revenues = new HashMap<>();


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Order order) {
        log.info("received: {}", order);

        //update revenue
        updateRevenue(order.category(), order.price() * order.quantity());
    }

    @Override
    public void onError(Throwable t) {
        log.error("error", t);
    }

    @Override
    public void onComplete() {
        log.info("completed.");

    }

    public void updateRevenue(String category, int totalPrice) {
        //update and print out
        var currentRevenue = revenues.getOrDefault(category, 0);
        revenues.put(category, currentRevenue + totalPrice);

        log.info("latest revenue: {}", revenues);

    }
}
