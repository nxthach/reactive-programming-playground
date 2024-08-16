package com.example.sec09.assignment;

import com.example.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Mono;

public class ProductService {

    private final ExternalServiceClient client;

    public ProductService(ExternalServiceClient client) {
        this.client = client;
    }

    public Mono<Product> getProduct(int productId) {
        return Mono.zip(
                        client.getProductName(productId),
                        client.getReview(productId),
                        client.getPrice(productId))
                .map(t -> new Product(
                        t.getT1(),
                        t.getT2(),
                        t.getT3()));

    }
}
