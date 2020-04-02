package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;

public class FactorialSquareCalculator extends RecursiveTask<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactorialSquareCalculator.class);

    private Long n;

    FactorialSquareCalculator(Long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        LOGGER.info("Calculate factorial square for {}", n);

        if (n <= 1) {
            return n;
        }

        FactorialSquareCalculator calculator
                = new FactorialSquareCalculator(n - 1);

        calculator.fork();

        return n * n + calculator.join();
    }
}