package com.example.sec03;

import com.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec06Log {

    public static void main(String[] args) {
        Flux.just(1, 2, 3, "sam")
                .subscribe(Util.subscriber());
    }
}
