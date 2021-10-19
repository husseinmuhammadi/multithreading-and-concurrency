package com.javastudio.tutorial.threadpoolexecutor;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TaskExecutorTest {
    private Logger logger = LoggerFactory.getLogger(TaskExecutorTest.class);

    @Test
    void simpleTestForExecutorService() throws InterruptedException {
        TaskExecutor.INSTANCE.init();

        for (int i = 0; i < 50; i++) {
            final int index = i + 1;
            logger.info("Preparing Task {} for executions", index);
            TaskExecutor.INSTANCE.getExecutor().execute(() -> {
                logger.info("Task {} started", index);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("Task {} completed", index);
            });
            // Thread.sleep(10);
        }

        logger.info("Wait until all tasks has been finished!");
        Thread.sleep(5000);

        TaskExecutor.INSTANCE.stop();
    }
}