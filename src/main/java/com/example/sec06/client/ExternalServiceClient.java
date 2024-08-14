package com.example.sec06.client;

import com.example.common.AbstractHttpClient;
import com.example.sec06.assignment.Helper;
import com.example.sec06.assignment.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Flux<Order> ordersStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(Helper::contructOrder);
    }
}
