package com.example.sec05;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .onErrorContinue((err, obj) -> log.error("error ==> {}", obj, err))
                .subscribe(Util.subscriber());
    }

    private static void onErrorComplete(){
        Mono.error(new RuntimeException("oops"))
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }

    private static void onErrorResume() {
        Mono.error(new RuntimeException())
                .onErrorResume(ArithmeticException.class, err -> fallback1())
                .onErrorResume(err -> fallback2())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.error(new RuntimeException("oops"));
    }

    private static void onErrorReturn() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? i / 0 : i)
                .map(i -> {
                    if (i == 3) {
                        throw new RuntimeException();
                    }
                    return i;
                })
                .onErrorReturn(IllegalAccessException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }
}
