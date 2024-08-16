package com.example.sec09;

import com.example.common.Util;
import com.example.sec09.helper.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;


public class Lec02StartWithUseCase {
    private static final Logger log = LoggerFactory.getLogger(Lec02StartWithUseCase.class);

    public static void main(String[] args) {

        var nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("SAM"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("MIKE"));

        nameGenerator.generateNames()
                .take(4)
                .subscribe(Util.subscriber("JACK"));

    }

}
