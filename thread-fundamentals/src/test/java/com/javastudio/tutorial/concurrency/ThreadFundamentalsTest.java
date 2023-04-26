package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ThreadFundamentalsTest {

    @Test
    void wait_until_thread_complete_the_action() throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i);
                try {
                    // thread to sleep for 1000 milliseconds
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + "has interrupted");
                }
            }
        });

        t.start();
        t.join();
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 100, 400})
    void should_complete_the_action_with_10_items_or_within_one_second(int elapsed) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            int i = 0;
            try {
                while (i < 100) {
                    System.out.println(i);
                    Thread.sleep(elapsed);

                    if (i == 10) {
                        break;
                    }

                    i++;
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has interrupted");
            }
        });
        t1.start();

        t1.join(1000);

        if (t1.isAlive()) {
            System.out.println(t1.getName() + " is Alive. Interrupting the thread " + t1.getName());
            t1.interrupt();
        }
    }
}