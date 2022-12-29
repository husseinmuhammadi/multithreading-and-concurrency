package com.javastudio.tutorial.wait;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

class SimpleScenarioUsingWaitAndNotifyTest {

    BlockingQueue<Integer> queue = new BlockingQueue<>(10);


    @Test
    void simulate() throws InterruptedException {
        Thread put = new Thread(this::put);
        Thread take = new Thread(this::take);

        put.start();
        take.start();

        TimeUnit.SECONDS.sleep(3);

    }

    private void take() {
        try {
            while (true) {
                queue.take();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void put() {
        int i = 0;
        try {
            while (true) {
                queue.put(i++);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

 class BlockingQueue<T> {

    private Queue<T> queue = new LinkedList<T>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }

        queue.add(element);
        notify(); // notifyAll() for multiple producer/consumer threads
    }

    public synchronized T take() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }

        T item = queue.remove();
        notify(); // notifyAll() for multiple producer/consumer threads
        return item;
    }
}

