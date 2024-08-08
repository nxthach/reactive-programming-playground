package com.example.sec03;

import com.example.common.Util;
import com.example.sec03.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec09FluxInterval {

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(500))
                .subscribe(Util.subscriber());


        Util.sleepSeconds(5);

    }
}
