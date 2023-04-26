package com.javastudio.tutorial.concurrency;

public class ArbitraryNumber {

    private boolean condition = false;

    public synchronized void odd() throws InterruptedException {
        int i = 1;
        while (i < 10) {

            if (condition)
                wait();

            System.out.println(i);
            i = i + 2;
            condition = !condition;

            notify();
        }
    }

    public synchronized void even() throws InterruptedException {
        int i = 2;
        while (i < 10) {

            if (!condition)
                wait();

            System.out.println(i);
            i = i + 2;
            condition = !condition;

            notify();
        }
    }
}
