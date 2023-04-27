package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TaskCompletion {

    private final Logger logger = LoggerFactory.getLogger(TaskCompletion.class);
    public void find() throws InterruptedException, ExecutionException {
        int nums = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(nums);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        while (nums-- > 0) {
            completionService.submit(new WorkerThread());
        }
        Integer firstValue = completionService.take().get();
        logger.info("FirstValue is " + firstValue);
        executorService.shutdownNow();
    }
}
