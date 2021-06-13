package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SquareCalculator {
    private Logger logger = LoggerFactory.getLogger(SquareCalculator.class);

    private final ExecutorService executor;

    public SquareCalculator(ExecutorService executor) {
        this.executor = executor;
    }

    public Future<Integer> calculate(Integer i) {
        return executor.submit(() -> {
            logger.info("Calculating result ...");
            Thread.sleep(3000);
            return i * i;
        });
    }

    public void shutdown() {
        executor.shutdown();
        logger.info("Executor service is shutdown successfully!");
    }
}
