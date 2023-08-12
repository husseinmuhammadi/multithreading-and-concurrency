package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

class ArithmeticOperationThreadSafetyTest {

    int count = 0;
    private final Object lock = new Object();

    void increase() {
        System.out.println(MessageFormat.format("Thread {0} started.", Thread.currentThread().getName()));

        for (int i = 0; i < 1_000_000_000; i++) {
            count++;
        }

        System.out.println(MessageFormat.format("Thread {0} finished.", Thread.currentThread().getName()));
    }

    @Test
    void should_increase_2_billion_times() throws Exception {
        Thread t1 = new Thread(this::increase, "Thread-1");
        Thread t2 = new Thread(this::increase, "Thread-2");

        Instant start = Instant.now();

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Index: " + this.count);

        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Time elapsed: " + timeElapsed);
    }
}
