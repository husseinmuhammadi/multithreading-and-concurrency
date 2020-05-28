package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {
        new Thread(Application::increase, "Thread-1").start();
        new Thread(Application::increase, "Thread-2").start();

        Thread.sleep(3000);
        LOGGER.info("Index: {}", index);
    }

    private static int index = 0;

    private static void increase() {
        for (int i = 0; i < 100000000; i++) {
            index++;
        }

        LOGGER.info("Thread {} has finished its activity.", Thread.currentThread().getName());
    }
}

