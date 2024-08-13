package com.example.sec05;

import com.example.common.Util;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Lec10Transform {
    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    public static void main(String[] args) {

        getCustomers()
                .transform(addDebugger())
                .subscribe();

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe();
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 5)
                .map(i -> new PurchaseOrder(
                        Util.faker().commerce().productName(),
                        i,
                        i * 10));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed."))
                .doOnError(err -> log.error("error", err));
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    record Customer(int id, String name) {}

    record PurchaseOrder(String productName, int price, int quantity){}


}
