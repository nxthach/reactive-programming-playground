package com.example.sec04;

import com.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    public static void main(String[] args) {

        Flux.create(emitter -> {
                    String country;
                    do {
                        country = Util.faker().country().name();
                        emitter.next(country);
                    } while (!country.equalsIgnoreCase("canada"));

                    emitter.complete();
                })
                .subscribe(Util.subscriber());
    }
}
