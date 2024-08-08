package com.example.sec03;

import com.example.common.Util;
import com.example.sec01.subscriber.SubscriberImpl;
import com.example.sec03.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec07FluxVsList {

    public static void main(String[] args) {

//        var list = NameGenerator.getNamesList(10);
//        System.out.println(list);

        var subscriber = new SubscriberImpl();

        NameGenerator.getNamesFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();


    }
}
