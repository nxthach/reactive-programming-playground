package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.assignment.ProductService;
import com.example.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
	sequentially and preserving order using concatenation.
 */

public class Lec13ConcatMap {

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        ProductService service = new ProductService(client);

        Flux.range(1, 10)
                .concatMap(service::getProduct)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(10);
    }
}
