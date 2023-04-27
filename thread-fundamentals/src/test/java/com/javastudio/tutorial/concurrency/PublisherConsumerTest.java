package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class PublisherConsumerTest {

    private PublisherConsumer broker;

    @BeforeEach
    void setUp() {
        broker = new PublisherConsumer();
    }

    @Test
    void name() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                broker.publish();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                broker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join(3000);
        t2.join(3000);
    }
}