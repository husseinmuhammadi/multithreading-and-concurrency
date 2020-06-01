package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        new Thread(Greeting3::sayHello, "Thread-1").start();
        new Thread(Greeting3::sayGoodbye, "Thread-2").start();
    }
}

/**
 * Greeting is thread safe
 */
class Greeting {
    private static final Logger LOGGER = LoggerFactory.getLogger(Greeting.class);

    static synchronized void sayHello() {
        try {
            Thread.sleep(3000);
            LOGGER.info("Hello");
        } catch (InterruptedException ignored) {
        }
    }

    static synchronized void sayGoodbye() {
        LOGGER.info("Goodbye");
    }
}












class Greeting2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Greeting2.class);
    private static final Object lock = Greeting2.class;

    public static void sayHello() {
        try {
            synchronized (lock) {
                Thread.sleep(3000);
                LOGGER.info("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public static void sayGoodbye() {
        synchronized (lock) {
            LOGGER.info("Goodbye");
        }
    }
}

class Greeting3 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Greeting3.class);
    private static final Object lock = Greeting3.class;

    public static void sayHello() {
        try {
            synchronized (lock) {
                Thread.sleep(3000);
                LOGGER.info("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public static synchronized void sayGoodbye() {
        LOGGER.info("Goodbye");
    }
}

class Greeting4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Greeting4.class);
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void sayHello() {
        try {
            synchronized (lock1) {
                Thread.sleep(3000);
                LOGGER.info("Hello");
            }
        } catch (InterruptedException ignored) {
        }
    }

    public static void sayGoodbye() {
        synchronized (lock2) {
            LOGGER.info("Goodbye");
        }
    }
}