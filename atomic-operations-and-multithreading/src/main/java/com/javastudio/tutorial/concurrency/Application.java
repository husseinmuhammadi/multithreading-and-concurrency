package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {
        new Thread(Application::increase, "Thread-1").start();
        new Thread(Application::increase, "Thread-2").start();

        Thread.sleep(3000);
        LOGGER.info("Index: {}", index);
    }

    private static final Object lock=new Object();
    private static int index = 0;

    private /*synchronized*/ static void increase() {
        for (int i = 0; i < 1000000000; i++) {
            // synchronized (lock) {
                index++;
            // }
        }

        LOGGER.info("Thread {} has finished its activity.", Thread.currentThread().getName());
    }
}

