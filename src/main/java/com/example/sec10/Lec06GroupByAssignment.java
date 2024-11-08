package com.example.sec10;

import com.example.common.Util;
import reactor.core.publisher.Flux;
import com.example.sec10.assignment.groupby.PurchaseOrder;
import com.example.sec10.assignment.groupby.OrderProcessingService;

import java.time.Duration;

public class Lec06GroupByAssignment {

    public static void main(String[] args) {
        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> PurchaseOrder.create());
    }
}
