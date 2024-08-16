package com.example.sec09.client;

import com.example.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getPrice(int productId){
        return this.httpClient.get()
                .uri("/demo05/price/" + productId)
                .responseContent()
                .asString()
                .next();

    }

    public Mono<String> getProductName(int productId){
        return this.httpClient.get()
                .uri("/demo05/product/" + productId)
                .responseContent()
                .asString()
                .next();

    }

    public Mono<String> getReview(int productId){
        return this.httpClient.get()
                .uri("/demo05/review/" + productId)
                .responseContent()
                .asString()
                .next();

    }
}
