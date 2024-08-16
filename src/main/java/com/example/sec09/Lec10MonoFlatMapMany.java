package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.applications.OrderService;
import com.example.sec09.applications.PaymentService;
import com.example.sec09.applications.UserService;

public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }
}
