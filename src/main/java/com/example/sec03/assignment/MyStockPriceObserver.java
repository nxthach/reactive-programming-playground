package com.example.sec03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyStockPriceObserver implements Subscriber<Integer> {

    private static final Logger log = LoggerFactory.getLogger(MyStockPriceObserver.class);

    private MyWallet myWallet;

    public MyStockPriceObserver(MyWallet myWallet) {
        this.myWallet = myWallet;
    }

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer price) {

        log.info("updated price: {}", price);

        //buy
        if (price < 90 && myWallet.getBalance() >= price) {

            myWallet.buyOne(price);

            log.info("bought a stock at {}, total quantity: {}, remaining balance: {}",
                    price, myWallet.getQuantity(), myWallet.getBalance());
        }

        //sell all
        if (price > 110 & myWallet.getQuantity() > 0) {
            log.info("selling {} quantities at {}", myWallet.getQuantity(), price);

            myWallet.sellAll(price);

            log.info("profit {}", myWallet.getProfit());

            this.subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable t) {
        log.error("error", t);
    }

    @Override
    public void onComplete() {
        log.info("completed!");

    }
}
