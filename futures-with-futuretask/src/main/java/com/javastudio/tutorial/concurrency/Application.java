package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
//            simulateExecutorServiceSample3();
            simulateForkJoinTask();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info("Simulate is finished!");
    }

    private static void simulateForkJoinTask() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(10L);
        forkJoinPool.execute(calculator);
        Long result = calculator.get();
        LOGGER.info("Result: {}", result);
    }
}
