package com.javastudio.tutorial.wait;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class SleepingThreadTest {

    @Test
    void traceThreadState() throws InterruptedException {

        Thread t1 = new Thread(this::infiniteLoop, "thread1");
        Thread t2 = new Thread(() -> monitorOtherThread(t1), "thread2");
        t2.start();

        TimeUnit.SECONDS.sleep(2);

        t1.start();

        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();
        t2.interrupt();

        t1.join();
        t2.join();
    }

    private void monitorOtherThread(Thread thread) {
        try {
            while (true) {
                System.out.println(thread.getState());
                TimeUnit.MILLISECONDS.sleep(400);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s has interrupted %n", Thread.currentThread().getName());
        }
    }

    private void infiniteLoop() {
        try {
            while (true) {
                for (int i = 0; i < 100_000_000; i++) {
                    if (i%1000000 == 0)
                        i++;
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.printf("%s has interrupted %n", Thread.currentThread().getName());
        }
    }
}

