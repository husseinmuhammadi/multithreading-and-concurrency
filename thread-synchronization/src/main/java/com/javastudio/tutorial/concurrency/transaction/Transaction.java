package com.javastudio.tutorial.concurrency.transaction;

public abstract class Transaction extends Thread {

    private final String transactionId;

    public Transaction(String transactionId, Runnable target) {
        super(target, transactionId);
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }
}