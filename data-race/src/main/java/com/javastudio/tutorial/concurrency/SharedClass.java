package com.javastudio.tutorial.concurrency;

public class SharedClass {
    private int x;
    private int y;

    public void increment() {
        x++;
        y++;
    }

    public void checkForDataRace() {
        if (y > x) {
            System.out.println("y > x - Data race detected!");
        }
    }
}
