package com.example.sec03;

import com.example.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec05FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 5)
                .log("range-map")
                .map(e -> Util.faker().name().firstName())
                .log("map-subscriber")
                .subscribe(Util.subscriber());
    }
}
