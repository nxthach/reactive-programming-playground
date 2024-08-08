package com.example.sec02;

import com.example.common.Util;
import com.example.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * To demo non-blocking IO
 * Ensure that the external service is up and running.
 */
public class Lec11NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        log.info("starting");

        for (int i = 0; i <= 100; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber("subscriber-" + i));
        }

        Util.sleepSeconds(2);

        log.info("ended");


    }

}
