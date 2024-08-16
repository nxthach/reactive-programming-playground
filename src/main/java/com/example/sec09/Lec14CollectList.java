package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.assignment.ProductService;
import com.example.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;


public class Lec14CollectList {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());
    }
}
