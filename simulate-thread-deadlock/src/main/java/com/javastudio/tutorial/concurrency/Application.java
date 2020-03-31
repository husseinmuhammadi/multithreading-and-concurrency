package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                LOGGER.info("{}: Holding lock 1 ...", Thread.currentThread().getName());
                LOGGER.info("{}: Waiting for lock 2 ...", Thread.currentThread().getName());
                sleep(1000);
                synchronized (lock2) {
                    LOGGER.info("{}: Holding lock 1 & 2 ...", Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                LOGGER.info("{}: Holding lock 2 ...", Thread.currentThread().getName());
                LOGGER.info("{}: Waiting for lock 1 ...", Thread.currentThread().getName());
                sleep(1000);
                synchronized (lock1) {
                    LOGGER.info("{}: Holding lock 1 & 2 ...", Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread1.start();
        thread2.start();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
