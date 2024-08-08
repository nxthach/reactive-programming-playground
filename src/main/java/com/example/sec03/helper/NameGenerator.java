package com.example.sec03.helper;

import com.example.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {
    public static List<String> getNamesList(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(e -> generateName())
                .toList();
    }

    public static Flux<String> getNamesFlux(int count) {
        return Flux.range(1, count)
                .map(e -> generateName());
    }

    private static String generateName() {
        Util.sleepSeconds(1);
        return Util.faker().name().firstName();
    }
}
