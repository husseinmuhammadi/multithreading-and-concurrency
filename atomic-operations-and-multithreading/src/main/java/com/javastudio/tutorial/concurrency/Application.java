package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {

    }
}

















class ArithmeticOperationThreadSafe {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArithmeticOperationThreadSafe.class);

    static int index = 0;

    static synchronized void increase() {
        LOGGER.info("Thread {} started.", Thread.currentThread().getName());
        for (int i = 0; i < 1000000000; i++) {
            index++;
        }

        LOGGER.info("Thread {} has finished its activity.", Thread.currentThread().getName());
    }
}

class ArithmeticOperationThreadSafe2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArithmeticOperationThreadSafe2.class);

    private static final Object lock = new Object();

    static int index = 0;

    static void increase() {
        LOGGER.info("Thread {} started.", Thread.currentThread().getName());
        for (int i = 0; i < 1000000000; i++) {
            synchronized (lock) {
                index++;
            }
        }

        LOGGER.info("Thread {} has finished its activity.", Thread.currentThread().getName());
    }
}
