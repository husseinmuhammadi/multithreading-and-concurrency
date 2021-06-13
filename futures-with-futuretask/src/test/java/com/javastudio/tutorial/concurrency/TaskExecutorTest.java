package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TaskExecutorTest {
    private Logger logger = LoggerFactory.getLogger(TaskExecutorTest.class);

    @Test
    void simpleTestForExecutorService() {
        TaskExecutor.INSTANCE.init();
        for (int i = 0; i < 200; i++) {
            int index = i;
            TaskExecutor.INSTANCE.getExecutor().execute(() -> logger.info("{}", index));
        }
    }
}