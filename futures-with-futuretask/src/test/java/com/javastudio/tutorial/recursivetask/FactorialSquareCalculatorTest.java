package com.javastudio.tutorial.recursivetask;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class FactorialSquareCalculatorTest {

    private Logger logger= LoggerFactory.getLogger(FactorialSquareCalculatorTest.class);

    @Test
    void simulateRecursiveTask() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(10L);
        forkJoinPool.execute(calculator);
        Long result = calculator.get();
        logger.info("Result: {}", result);
    }

}