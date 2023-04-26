package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbitraryNumberTest {

    private ArbitraryNumber arbitraryNumber;

    @BeforeEach
    void setUp() {
        arbitraryNumber = new ArbitraryNumber();
    }

    @Test
    void printNumbers() throws Exception{
        Thread t1 = new Thread(this::generateOdd);
        Thread t2 = new Thread(this::generateEven);

        t1.start();
        t2.start();
    }

    private void generateOdd() {
        try {
            arbitraryNumber.odd();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void generateEven() {
        try {
            arbitraryNumber.even();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}