package com.javastudio.tutorial.concurrency;

abstract class Transaction extends Thread {
    final String id;
    final Account from;
    final Account to;
    final double amount;

    Transaction(String id, Account from, Account to, double amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
