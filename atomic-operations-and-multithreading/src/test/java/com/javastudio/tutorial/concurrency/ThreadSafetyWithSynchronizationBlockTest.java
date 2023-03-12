package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

class ThreadSafetyWithSynchronizationBlockTest {

    private static final Object lock = new Object();

    static int count = 0;

    private void increase() {
        System.out.println(MessageFormat.format("Thread {0} started.", Thread.currentThread().getName()));

        for (int i = 0; i < 1000000000; i++) {
            synchronized (lock) {
                count++;
            }
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
