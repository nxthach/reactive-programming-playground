package com.example.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.UnaryOperator;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);

    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static void main(String[] args) {
        var mono = Mono.just(1);

        mono.subscribe(subscriber("Sub1"));
        mono.subscribe(subscriber("Sub2"));
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return flux -> flux
                .doOnSubscribe(i -> log.info("subscribing to {}", name))
                .doOnCancel(() -> log.info("cancelling {}", name))
                .doOnComplete(() -> log.info("{} completed", name));
    }

    public static Faker faker() {
        return faker;
    }
}
