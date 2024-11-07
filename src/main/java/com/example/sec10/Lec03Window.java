package com.example.sec10;

import com.example.common.Util;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03Window {

    public static void main(String[] args) {
        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(Lec03Window::processEvent)
                .subscribe();


        Util.sleepSeconds(60);
    }



    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event-" + (i + 1));
    }

    private static Publisher<?> processEvent(Flux<String> flux) {
        return flux.doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println);
    }
}
