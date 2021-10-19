package com.javastudio.tutorial.executorservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class SquareCalculatorTest {

    private Logger logger = LoggerFactory.getLogger(SquareCalculatorTest.class);

    private SquareCalculator squareCalculatorWithSingleThreadExecutor;
    private SquareCalculator squareCalculatorWithFixedThreadPool;

    @BeforeEach
    void setUp() {
        squareCalculatorWithSingleThreadExecutor = new SquareCalculator(Executors.newSingleThreadExecutor());
        squareCalculatorWithFixedThreadPool = new SquareCalculator(Executors.newFixedThreadPool(2));
    }

    @Test
    void givenExecutorServiceWithSingleThreadExecutor_whenRunATimeConsumingMethod_thenFutureWillWaitUntilResponseCalculated() throws ExecutionException, InterruptedException, TimeoutException {
        logger.info("Calculating square of {} ...", 12);
        Future<Integer> future = squareCalculatorWithSingleThreadExecutor.calculate(12);
        logger.info("The result will be populated after a while ...");
        Integer result = future.get();
        logger.info("The result is {}", result);
        Assertions.assertEquals(result, 144);
    }

    @Test
    void givenExecutorServiceWithFixedThreadPool_whenRunATimeConsumingMethod_thenFutureWillWaitUntilResponseCalculated() throws ExecutionException, InterruptedException, TimeoutException {
        logger.info("Calculating square of {} ...", 12);
        Future<Integer> future = squareCalculatorWithFixedThreadPool.calculate(12);
        logger.info("The result will be populated after a while ...");
        Integer result = future.get();
        logger.info("The result is {}", result);
        Assertions.assertEquals(result, 144);
    }

    @Test
    void givenExecutorService_whenRunATimeConsumingMethod_thenItWillTakeTimeMoreThanOneSecond() throws ExecutionException, InterruptedException, TimeoutException {
        Assertions.assertThrows(TimeoutException.class, () -> {
            Future<Integer> future = squareCalculatorWithSingleThreadExecutor.calculate(12);
            Integer result = future.get(1000, TimeUnit.MILLISECONDS);
        });
    }

    private void simulateExecutorServiceSample2() throws ExecutionException, InterruptedException, TimeoutException {
        SquareCalculator squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());
        logger.info("Calculating square of {} ...", 100);
        Future<Integer> future = squareCalculator.calculate(100);
        logger.info("The result will be populated after a while ...");
        if (!future.isDone()) {
            logger.info("Calculating is in progress ...");
            Thread.sleep(300);
        }
        if (!future.isDone()) {
            logger.info("Again calculating is in progress ...");
            Thread.sleep(300);
        }
        if (!future.isDone()) {
            logger.warn("No longer with don't need the answer ... canceling the task");
            boolean cancel = future.cancel(true);
        }
        if (!future.isCancelled()) {
            Integer result = future.get();
            logger.info("The result is {}", result);
        }
        squareCalculator.shutdown();
    }

    private void simulateExecutorServiceSample3() throws InterruptedException, ExecutionException {
        SquareCalculator squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());

        Future<Integer> future1 = squareCalculator.calculate(10);
        Future<Integer> future2 = squareCalculator.calculate(100);
        Future<Integer> future3 = squareCalculator.calculate(101);

        while (!(future1.isDone() && future2.isDone() && future3.isDone())) {
            System.out.println(
                    String.format(
                            "future1 is %s and future2 is %s and future3 is %s",
                            future1.isDone() ? "done" : "not done",
                            future2.isDone() ? "done" : "not done",
                            future3.isDone() ? "done" : "not done"
                    )
            );
            Thread.sleep(300);
        }

        Integer result1 = future1.get();
        Integer result2 = future2.get();
        Integer result3 = future2.get();

        logger.info("{} and {} and {}", result1, result2, result3);

        squareCalculator.shutdown();
    }
}