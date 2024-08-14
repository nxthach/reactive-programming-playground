package com.example.sec06;

import com.example.common.Util;
import com.example.sec06.assignment.*;
import com.example.sec06.client.ExternalServiceClient;

import java.time.Duration;

public class Lec06Assignment {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        var orderFlux = client.ordersStream()
                .publish()
                .refCount(2);

        //
        orderFlux.subscribe(inventoryService::consume);
        orderFlux.subscribe(revenueService::consume);

        //
        inventoryService.stream()
                        .subscribe(Util.subscriber("inventory"));
        revenueService.stream()
                .subscribe(Util.subscriber("revenue"));


        Util.sleepSeconds(30);
    }

    private static void myAssignment(){
        ExternalServiceClient client = new ExternalServiceClient();

        var orderFlux = client.ordersStream().publish().refCount(2);

        //inventory service
        orderFlux
                .delayElements(Duration.ofSeconds(2))
                .subscribe(new MyInventoryService());

        //revenue
        orderFlux
                .delayElements(Duration.ofSeconds(2))
                .subscribe(new MyRevenueService());

        Util.sleepSeconds(20);
    }
}
