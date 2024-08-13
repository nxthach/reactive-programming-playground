package com.example.sec05.client;

import com.example.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Mono<String> getProductName(int id) {
        log.info("getting product at id={}", id);
        return getProductName("/demo03/product/" + id);
    }

    public Mono<String> emptyFallback(int id) {
        log.info("getting product once got empty at id={}", id);
        return getProductName("/demo03/empty-fallback/product/" + id);
    }

    public Mono<String> timeoutFallback(int id) {
        log.info("getting product once got timeout at id={}", id);
        return getProductName("/demo03/timeout-fallback/product/" + id);
    }

    private Mono<String> getProductName(String uri){
        return this.httpClient.get()
                .uri(uri)
                .responseContent()
                .asString()
                .next();
    }
}
