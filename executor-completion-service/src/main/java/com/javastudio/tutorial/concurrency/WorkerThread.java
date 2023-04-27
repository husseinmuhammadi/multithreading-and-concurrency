package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;

public class WorkerThread implements Callable<Integer> {

    private final Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    @Override
    public Integer call() throws Exception {
        int nextInt = new Random().nextInt(10000);
        try {
            logger.info("I will cost {} ms to finish job. --" + Thread.currentThread().getName(), nextInt);
            Thread.sleep(nextInt);
        } catch (InterruptedException e) {
            logger.warn("I am interrupted.--" + Thread.currentThread().getName());
            throw new RuntimeException();
        }
        logger.info("I am finish.--" + Thread.currentThread().getName());
        return nextInt;
    }
}

