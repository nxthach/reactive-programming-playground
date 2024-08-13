package com.example.sec05;

import com.example.common.Util;
import com.example.sec05.assignment.ProductService;
import com.example.sec05.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Lec11Assignment {
    private static final Logger log = LoggerFactory.getLogger(Lec11Assignment.class);

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        ProductService service = new ProductService(client);

        for (int i = 0; i < 5; i++) {
            service.getProductName(i)
                    .doOnNext(item -> log.info("received: {}", item))
                    .doOnTerminate(() -> log.info("completed."))
                    .subscribe();
        }

        Util.sleepSeconds(5);

    }


}
