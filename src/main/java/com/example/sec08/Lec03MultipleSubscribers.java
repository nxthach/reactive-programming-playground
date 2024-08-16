package com.example.sec08;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


public class Lec03MultipleSubscribers {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

    public static void main(String[] args) {

        var flux = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating: {}", state);
                            sink.next(state);
                            return ++state;
                        }
                ).cast(Integer.class)
                .subscribeOn(Schedulers.boundedElastic());

        flux
                .limitRate(5) // no more 5 items for each produce
                .publishOn(Schedulers.boundedElastic())
                .map(Lec03MultipleSubscribers::timeConsumingTask)
                .subscribe(Util.subscriber("sub1"));

        flux
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(60);
    }

    private static int timeConsumingTask(int i) {
        Util.sleepSeconds(1);

        return i;
    }
}
