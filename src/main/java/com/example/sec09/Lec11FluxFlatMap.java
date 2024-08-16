package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.applications.OrderService;
import com.example.sec09.applications.UserService;

public class Lec11FluxFlatMap {

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(user -> OrderService.getUserOrders(user.userId()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
