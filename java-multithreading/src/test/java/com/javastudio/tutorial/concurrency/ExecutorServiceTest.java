package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceTest {

    private static final int THREAD_POOL_THREAD_COUNT = 10;

    @Test
    void executor_service_will_run_on_different_thread() {

    }

    @Test
    void executor_service_with_with_run_on_different_threads() throws InterruptedException {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_THREAD_COUNT);
        IntStream.rangeClosed(1, 10).mapToObj(String::valueOf).map(i -> runnable).forEach(executorService::submit);
        TimeUnit.of(ChronoUnit.MILLIS).sleep(200);
        executorService.shutdown();
    }
}
