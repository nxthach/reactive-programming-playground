package com.example.sec06;

import com.example.common.Util;
import com.example.sec04.helper.NameGenerator;
import com.example.sec05.assignment.ProductService;
import com.example.sec05.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

public class Lec01ColdPublisher {
    private static final Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {
        multipleSubscriberButCanManipulateOutside();

    }

    private static void multipleSubscriberButCanManipulateOutside(){
        AtomicInteger atomicInteger = new AtomicInteger();
        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for (int i = 0; i < 3; i++) {
                fluxSink.next(atomicInteger.incrementAndGet());

            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
    }

    //cold publisher
    //last sub will be applied
    private static void singleSubscriber() {
        var generator = new NameGenerator();
        var flux = Flux.create(generator);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 3; i++) {
            //use sink to send data
            //once flux sink have data, it will send data to subscriber
            generator.generate();
        }
    }


}
