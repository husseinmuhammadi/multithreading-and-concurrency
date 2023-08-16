package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ExecutorServiceTest {

    private static final int THREAD_POOL_THREAD_COUNT = 10;

    @Test
    void executor_service_will_run_on_different_thread() {

    }

    @Test
    void executor_service_with_with_fix_thread_pool_run_on_different_threads() throws InterruptedException {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_THREAD_COUNT);
        IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).map(i -> runnable).forEach(executorService::submit);
        TimeUnit.of(ChronoUnit.MILLIS).sleep(200);
        executorService.shutdown();
    }


    private static final int THREADS_COUNT = 10;

    @Test
    void executor_service_with_single_thread_executor() throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println(Thread.currentThread().getName());
        executorService.submit(() -> {
            try {
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.of(ChronoUnit.MILLIS).sleep(1000);
        executorService.shutdown();
    }

    @Test
    void executor_service_with_fix_thread_pool_should_run_on_() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

        for (int i = 0; i < THREADS_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(200);
                    System.out.println(
                            Thread.currentThread().getName()
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        TimeUnit.of(ChronoUnit.MILLIS).sleep(1000);
        executorService.shutdown();
    }

    @Test
    void executor_service_with_callable_returns_future() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> task = () -> {
            TimeUnit.of(ChronoUnit.MILLIS).sleep(3000);
            return Thread.currentThread().getName();
        };

        Future<String> future = executorService.submit(task);

        System.out.println("Task started and doing something else while task populating response");

        System.out.println(future.get());
        executorService.shutdown();
    }

    @Test
    void executor_service_with_callable_returns_future_2() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_THREAD_COUNT);
        IntStream.rangeClosed(1, 10).mapToObj(i -> 2000 - i * 200).map(timeout ->
                (Callable<String>) () -> {
                    TimeUnit.of(ChronoUnit.MILLIS).sleep(timeout);
                    return Thread.currentThread().getName();
                }
        ).forEach(task -> {
            Future<String> future = executorService.submit(task);
            try {
                System.out.println(future.get(5000, TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }

    @Test
    void executor_service_execute_future_task() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_THREAD_COUNT);

        FutureTask<String> task = new FutureTask<>(() -> {
            TimeUnit.of(ChronoUnit.MILLIS).sleep(3000);
            return Thread.currentThread().getName();
        });

        executorService.execute(task);
        System.out.println("Task is executing and do some other works while it is executing");

        System.out.println(task.get());
        executorService.shutdown();
    }

    /**
     * https://howtodoinjava.com/java/multi-threading/executor-service-cancel-task/
     */
    @Test
    void cancel_task_in_waiting_queue() {

    }

    @Test
    void cancelling_a_task_in_execution() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {

        });

        executorService.shutdown();
    }

    /**
     * https://stackoverflow.com/questions/70152050/how-to-find-the-result-of-first-thread-that-finishes-successfully-in-java
     */
    @Test
    void proceed_with_the_result_of_first_thread() {
        Callable<Integer> callable = () -> {
            int nextInt = new Random().nextInt(10000);
            try {
                System.out.println("I will cost " + nextInt + " ms to finish job.--" + Thread.currentThread().getName());
                Thread.sleep(nextInt);
            } catch (InterruptedException ite) {
                System.out.println("I am interrupted.--" + Thread.currentThread().getName());
                return -1;
            }
            System.out.println("I am finish.--" + Thread.currentThread().getName());
            return nextInt;
        };
    }
}
