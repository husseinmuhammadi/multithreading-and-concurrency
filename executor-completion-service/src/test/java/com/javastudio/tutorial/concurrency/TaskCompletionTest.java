package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class TaskCompletionTest {

    private TaskCompletion taskCompletion;

    @BeforeEach
    void setUp() {
        taskCompletion = new TaskCompletion();
    }

    @Test
    void should_get_first_result_and_interrupt_the_other_threads() throws ExecutionException, InterruptedException {
        taskCompletion.find();
    }
}