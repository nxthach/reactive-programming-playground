package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.helper.Kayak;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec06MergeUseCase {
    private static final Logger log = LoggerFactory.getLogger(Lec06MergeUseCase.class);


    public static void main(String[] args) {

        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);


    }
}
