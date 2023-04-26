package com.javastudio.tutorial.wait;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class PublisherSubscriberTest {

    @Test
    void demo() throws InterruptedException {
        Queue<String> q = new LinkedList<>();
        boolean exit = false;
        Producer p = new Producer(q, exit);
        p.start();
        Consumer c = new Consumer(q, exit);
        c.start();
        TimeUnit.SECONDS.sleep(10);
    }
}

class Producer extends Thread {
    private volatile Queue<String> sharedQueue;
    private volatile boolean bExit;

    public Producer(Queue<String> myQueue, boolean bExit) {
        this.sharedQueue = myQueue;
        this.bExit = bExit;
    }

    public void run() {
        while (!bExit) {
            synchronized (sharedQueue) {
                while (sharedQueue.isEmpty()) {
                    String item = String.valueOf(System.nanoTime());
                    sharedQueue.add(item);
                    System.out.println("Producer added : " + item);
                    try {
                        System.out.println("Producer sleeping by calling wait: " + item);
                        TimeUnit.MILLISECONDS.sleep(2000);
                        sharedQueue.wait();
                        System.out.println("Producer wake up: ");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Consumer extends Thread {
    private volatile Queue<String> sharedQueue;
    private volatile boolean bExit;

    public Consumer(Queue<String> myQueue, boolean bExit) {
        this.sharedQueue = myQueue;
        this.bExit = bExit;
    }

    public void run() {
        while (!bExit) {
            synchronized (sharedQueue) {
                while (!sharedQueue.isEmpty()) {
                    String item = sharedQueue.poll();
                    System.out.println("Consumer removed : " + item);
                    System.out.println("Consumer notifying Producer: " + item);
                    sharedQueue.notify();
                }
            }
        }
    }
}

