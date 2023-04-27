package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class PublisherConsumer {

    private final Logger logger = LoggerFactory.getLogger(PublisherConsumer.class);
    private final Object lock = new Object();
    private boolean condition = false;

    public void publish() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {

                while (condition)
                    lock.wait();

                logger.info("Item {} published", i);

                condition = !condition;
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {

                while (!condition)
                    lock.wait();

                logger.info("Item {} consumed", i);

                condition = !condition;
                lock.notify();
            }
        }

    }
}
