package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.applications.PaymentService;
import com.example.sec09.applications.UserService;

public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        UserService.getUserId("sam")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());

    }
}
