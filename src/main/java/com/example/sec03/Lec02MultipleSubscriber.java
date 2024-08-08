package com.example.sec03;

import com.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscriber {

    public static void main(String[] args) {
        var flux = Flux.just(1, 2, 3, 4, 5, 6);

        flux.subscribe(Util.subscriber("sub1"));

        flux.filter(e -> e < 5)
                .subscribe(Util.subscriber("sub2"));

        flux.map(e -> e + "A")
                .subscribe(Util.subscriber("sub3"));
    }
}
