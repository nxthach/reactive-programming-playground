package com.example.sec10;

import com.example.common.Util;
import com.example.sec10.assignment.window.FileWriter;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {

    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> FileWriter.create(flux,
                        Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();


        Util.sleepSeconds(60);
    }



    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event-" + (i + 1));
    }
}
