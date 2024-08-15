package com.example.sec07;

import com.example.common.Util;
import com.example.sec07.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;


public class Lec06EventLoopIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec06EventLoopIssueFix.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 0; i <= 5; i++) {
            client.getProductName(i)
                    .publishOn(Schedulers.boundedElastic())
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber("subscriber-" + i));
        }

        Util.sleepSeconds(7);
    }

    private static String process(String input){
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
