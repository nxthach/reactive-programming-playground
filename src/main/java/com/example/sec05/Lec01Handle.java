package com.example.sec05;

import com.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    public static void main(String[] args) {

        Flux.range(1, 10)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-1);
                        case 4 -> {}
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                }).subscribe(Util.subscriber());
    }
}
