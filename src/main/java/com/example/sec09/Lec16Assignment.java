package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.applications.*;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec16Assignment {

    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);

    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                PaymentService.getUserBalance(user.userId()),
                OrderService.getUserOrders(user.userId()).collectList()

        ).map(t -> new UserInformation(user.userId(), user.userName(), t.getT1(), t.getT2()));
    }
}
