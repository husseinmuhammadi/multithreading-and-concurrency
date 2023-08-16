package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

class ThreadTest {
    @Test
    void interrupt_running_thread() throws InterruptedException {
        Thread t = new WorkerThread();
        t.start();
        System.out.println("Started");
        t.interrupt();
        t.join();
        System.out.println("Joined");
    }
}

class WorkerThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println("I am interrupted.--" + Thread.currentThread().getName());
        }
    }
}