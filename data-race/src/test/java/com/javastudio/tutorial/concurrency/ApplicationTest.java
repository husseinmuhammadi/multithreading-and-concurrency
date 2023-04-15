package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

class ApplicationTest {

    @Test
    void simulateDataRace() {
        SharedClass sharedClass = new SharedClass();

        Thread incrementThread = new Thread(()->{
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });

        Thread checkForDataRaceThread = new Thread(()->{
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }
        });

        incrementThread.start();
        checkForDataRaceThread.start();
    }
}