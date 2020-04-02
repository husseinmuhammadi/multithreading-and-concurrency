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

    private static void simulateExecutorServiceSample() throws ExecutionException, InterruptedException, TimeoutException {
        SquareCalculator squareCalculator = new SquareCalculator();
        LOGGER.info("Calculating square of {} ...", 100);
        Future<Integer> future = squareCalculator.calculate(100);
        LOGGER.info("The result will be populated after a while ...");
        Integer result = future.get(500, TimeUnit.MILLISECONDS);
        LOGGER.info("The result is {}", result);
    }

    private static void simulateExecutorServiceSample2() throws ExecutionException, InterruptedException, TimeoutException {
        SquareCalculator squareCalculator = new SquareCalculator();
        LOGGER.info("Calculating square of {} ...", 100);
        Future<Integer> future = squareCalculator.calculate(100);
        LOGGER.info("The result will be populated after a while ...");
        if (!future.isDone()) {
            LOGGER.info("Calculating is in progress ...");
            Thread.sleep(300);
        }
        if (!future.isDone()) {
            LOGGER.info("Again calculating is in progress ...");
            Thread.sleep(300);
        }
        if (!future.isDone()) {
            LOGGER.warn("No longer with don't need the answer ... canceling the task");
            boolean cancel = future.cancel(true);
        }
        if (!future.isCancelled()) {
            Integer result = future.get();
            LOGGER.info("The result is {}", result);
        }
        squareCalculator.shutdown();
    }

    private static void simulateExecutorServiceSample3() throws InterruptedException, ExecutionException {
        SquareCalculator squareCalculator = new SquareCalculator();

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

        LOGGER.info("{} and {} and {}", result1, result2, result3);

        squareCalculator.shutdown();
    }

    private static void simulateForkJoinTask() throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FactorialSquareCalculator calculator = new FactorialSquareCalculator(10L);
        forkJoinPool.execute(calculator);
        Long result = calculator.get();
        LOGGER.info("Result: {}", result);
    }
}
