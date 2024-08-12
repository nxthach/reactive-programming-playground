package com.example.sec04;

import com.example.common.Util;
import com.example.sec03.assignment.MyStockPriceObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08FluxGenerateWithState {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    public static void main(String[] args) {

        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    var country = Util.faker().country().name();
                    log.info("generated country: {}", country);
                    sink.next(country);

                    counter++;
                    if (counter == 10 || country.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }

                    return counter;
                }).subscribe(Util.subscriber());

    }


}
