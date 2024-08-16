package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.applications.OrderService;
import com.example.sec09.applications.UserService;
import com.example.sec09.assignment.ProductService;
import com.example.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        ProductService service = new ProductService(client);

        Flux.range(1, 10)
                .flatMap(service::getProduct)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(10);
    }
}
