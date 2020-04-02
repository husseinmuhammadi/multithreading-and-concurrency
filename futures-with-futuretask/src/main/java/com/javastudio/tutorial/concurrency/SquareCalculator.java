package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SquareCalculator.class);

    private ExecutorService executor
            // = Executors.newSingleThreadExecutor();
            = Executors.newFixedThreadPool(2);

    public Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            LOGGER.info("Calculating the square of {} ... (It will take time about 3 seconds)", input);
            Thread.sleep(3000);
            return input * input;
        });
    }

    public void shutdown() {
        executor.shutdown();
        LOGGER.info("Executor service is shutdown successfully!");
    }
}
