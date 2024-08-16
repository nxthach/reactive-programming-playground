package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.assignment.ProductService;
import com.example.sec09.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec08ZipAssignment {
    private static final Logger log = LoggerFactory.getLogger(Lec08ZipAssignment.class);


    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        ProductService service = new ProductService(client);

        for (int i = 0; i < 10; i++) {
            service.getProduct(i)
                    .subscribe(Util.subscriber());
        }


        Util.sleepSeconds(10);

    }
}
