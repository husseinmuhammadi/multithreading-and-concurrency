package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        final Account accA = new Account("Acc 1");
        final Account accB = new Account("Acc 2");

        accA.deposit(1000.00);
        accB.deposit(1000.00);
        simulateTransferUsingMultiThread(accA, accB);
        // simulateTransferWithThreadDeadLock(accA, accB);
    }

    private static void simulateTransferUsingMultiThread(Account accA, Account accB) {
        Transaction t1 = new Transfer("T01", accA, accB, 100.00);
        Transaction t2 = new Transfer("T02", accB, accA, 500.00);

        t1.start();
        t2.start();
    }

    private static void simulateTransferWithThreadDeadLock(Account accA, Account accB) {
        Transaction t1 = new TransferWithThreadDeadLock("T01", accA, accB, 100.00);
        Transaction t2 = new TransferWithThreadDeadLock("T02", accB, accA, 500.00);

        t1.start();
        t2.start();
    }
}
