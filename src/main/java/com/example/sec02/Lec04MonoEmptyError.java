package com.example.sec02;

import com.example.common.Util;
import com.example.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    private static final Logger log = LoggerFactory.getLogger(Lec04MonoEmptyError.class);

    public static void main(String[] args) {

        getUsername(1)
                .subscribe(Util.subscriber());

        System.out.println("---");

        getUsername(2)//empty
                .subscribe(Util.subscriber());

        System.out.println("---");

        getUsername(3)//error
                .subscribe(Util.subscriber());

        System.out.println("---");

        getUsername(4)//error handle
                .subscribe(
                        e -> log.info("received: {}", e),
                        err -> log.error("TODO: will handle later")
                );
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("same");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }
}
