package com.example.sec05;

import com.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUnitAssignment {
    public static void main(String[] args) {

        Flux.<String>generate(
                        synchronousSink -> {
                            var country = Util.faker().country().name();
                            synchronousSink.next(country);
                        })
                .handle((item, sink) -> {
                    sink.next(item);
                    if (item.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                })
                .subscribe(Util.subscriber());
    }
}
