package com.bosch.si.as;

import java.util.concurrent.CountDownLatch;

/**
 * Created by fnumrich on 13.02.16.
 */
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
