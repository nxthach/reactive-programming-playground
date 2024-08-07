package com.example.sec02;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

/**
 * Creating publisher is a lightweight operation
 * Executing time-consuming business logic should be delay
 */

public class Lec09PublisherCreateVsExecution {
    private static final Logger log = LoggerFactory.getLogger(Lec09PublisherCreateVsExecution.class);

    public static void main(String[] args) {


    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("generation name");
            return Util.faker().name().firstName();
        });
    }
}
