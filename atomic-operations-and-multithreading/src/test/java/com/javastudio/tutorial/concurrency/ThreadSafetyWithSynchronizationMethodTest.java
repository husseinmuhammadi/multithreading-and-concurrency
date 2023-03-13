package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

class ThreadSafetyWithSynchronizationMethodTest {

    static int count = 0;

    private synchronized void increase() {
        System.out.println(MessageFormat.format("Thread {0} started.", Thread.currentThread().getName()));

        for (int i = 0; i < 1000000000; i++) {
                count++;
        }

        System.out.println(MessageFormat.format("Thread {0} finished.", Thread.currentThread().getName()));
    }

    @Test
    void should_increase_2_billion_times() throws Exception {
        new Thread(this::increase, "Thread-1").start();
        new Thread(this::increase, "Thread-2").start();

        Thread.sleep(3000);
        System.out.println("Index: " + count);
    }
}
