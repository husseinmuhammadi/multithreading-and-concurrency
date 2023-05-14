package com.javastudio.tutorial.concurrency;

import java.util.function.IntConsumer;

public class FizzBuzz {
    private final int n;
    private int i = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (i <= n) {
            if (i % 5 != 0 && i % 3 == 0) {
                printFizz.run();
                i++;
                notifyAll();
            } else
                wait();
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (i <= n) {
            if (i % 5 == 0 && i % 3 != 0) {
                printBuzz.run();
                i++;
                notifyAll();
            } else
                wait();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (i <= n) {
            if (i % 15 == 0) {
                printFizzBuzz.run();
                i++;
                notifyAll();
            } else
                wait();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            if (i % 5 != 0 && i % 3 != 0) {
                printNumber.accept(i++);
                notifyAll();
            } else
                wait();
        }
    }
}
