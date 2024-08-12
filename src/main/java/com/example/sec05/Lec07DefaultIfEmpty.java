package com.example.sec05;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec07DefaultIfEmpty {
    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultIfEmpty.class);

    public static void main(String[] args) {

        Flux.empty()
                .defaultIfEmpty("DEFAULT")
                .subscribe(Util.subscriber());
    }
}
