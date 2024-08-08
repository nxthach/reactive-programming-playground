package com.example.sec03;

import com.example.common.Util;
import com.example.sec01.subscriber.SubscriberImpl;
import com.example.sec03.client.ExternalServiceClient;
import com.example.sec03.helper.NameGenerator;

/**
 * To demo non-blocking IO with streaming messages
 * Ensure that the external service is up and running
 */
public class Lec08NonBlockingStreamingMessages {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        var flux = client.getFirstNames();

        flux.subscribe(Util.subscriber("sub1"));

        //flux.subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(6);

    }
}
