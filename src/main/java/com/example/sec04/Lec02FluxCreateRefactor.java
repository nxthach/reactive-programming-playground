package com.example.sec04;

import com.example.common.Util;
import com.example.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    public static void main(String[] args) {

        var generator = new NameGenerator();
        var flux = Flux.create(generator);

        flux.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            //use sink to send data
            //once flux sink have data, it will send data to subscriber
            generator.generate();
        }

    }
}
