package com.javastudio.tutorial.threadpoolexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public enum TaskExecutor {
    INSTANCE;

    private Logger logger = LoggerFactory.getLogger(TaskExecutor.class);

    private static final int EXECUTOR_ASYNC_POOL_SIZE = 4;
    private static final int EXECUTOR_ASYNC_POOL_SIZE_MAX = 5;
    private static final long EXECUTOR_ASYNC_POOL_KEEP_ALIVE = 2;
    private static final int EXECUTOR_ASYNC_QUEUE_CAPACITY = 20;

    private ExecutorService executorService;
    private AtomicInteger numberOfTasks = new AtomicInteger(0);

    public void init() {
        executorService = new ThreadPoolExecutor(
                EXECUTOR_ASYNC_POOL_SIZE,
                EXECUTOR_ASYNC_POOL_SIZE_MAX,
                EXECUTOR_ASYNC_POOL_KEEP_ALIVE,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(EXECUTOR_ASYNC_QUEUE_CAPACITY, true),
                new ThreadPoolExecutor.DiscardOldestPolicy()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                logger.info("Task started, Number of tasks: {}", numberOfTasks.incrementAndGet());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                logger.info("Task stopped, Number of tasks: {}", numberOfTasks.decrementAndGet());
            }
        };
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public void stop() {
        executorService.shutdown();
    }
}
