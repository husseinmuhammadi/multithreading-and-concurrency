package com.javastudio.tutorial.concurrency;

public class Application {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName());
                sleep(1000);
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName());
                sleep(1000);
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        thread1.setName("Thread1");
        thread2.setName("Thread2");
        thread1.start();
        thread2.start();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
