package com.example.sec05.assignment;

import com.example.sec05.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    ExternalServiceClient client;

    public ProductService(ExternalServiceClient client) {
        this.client = client;
    }

    public Mono<String> getProductName(int id) {
        return client.getProductName(id)
                .timeout(Duration.ofSeconds(2), client.timeoutFallback(id))
                .switchIfEmpty(client.emptyFallback(id));
    }
}
