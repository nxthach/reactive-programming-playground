package com.example.sec03.assignment;

import com.example.common.Util;
import com.example.sec03.client.ExternalServiceClient;

public class StockPriceObserverDemo {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();

        //client.getStockPrice().subscribe(subscriber);
        client.getStockPrice().subscribe(new MyStockPriceObserver(new MyWallet()));

        Util.sleepSeconds(20);
    }
}
