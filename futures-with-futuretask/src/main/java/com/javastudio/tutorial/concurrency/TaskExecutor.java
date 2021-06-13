package com.javastudio.tutorial.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public enum TaskExecutor {
    INSTANCE;

    private static final int EXECUTOR_ASYNC_POOL_SIZE = 100;
    private static final int EXECUTOR_ASYNC_POOL_SIZE_MAX = 100;
    private static final long EXECUTOR_ASYNC_POOL_KEEP_ALIVE = 2;
    private static final int EXECUTOR_ASYNC_QUEUE_CAPACITY = 100;

    private ExecutorService executorService;

    public void init() {
        executorService = new ThreadPoolExecutor(
                EXECUTOR_ASYNC_POOL_SIZE,
                EXECUTOR_ASYNC_POOL_SIZE_MAX,
                EXECUTOR_ASYNC_POOL_KEEP_ALIVE,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(EXECUTOR_ASYNC_QUEUE_CAPACITY, true),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public void stop() {
        executorService.shutdown();
    }
}
