package com.example.sec03;

import com.example.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec10FluxEmptyError {

    public static void main(String[] args) {

        Flux.empty()
                .subscribe(Util.subscriber());

        Flux.error(new RuntimeException("oops"))
                .subscribe(Util.subscriber());



    }
}
