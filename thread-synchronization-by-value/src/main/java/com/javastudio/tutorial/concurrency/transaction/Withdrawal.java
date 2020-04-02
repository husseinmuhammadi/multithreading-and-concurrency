package com.javastudio.tutorial.concurrency.transaction;

import com.javastudio.tutorial.concurrency.account.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Withdrawal extends Transaction {
    private static final Logger LOGGER = LoggerFactory.getLogger(Withdrawal.class);

    public Withdrawal(String transactionId, Account account, double amount) {
        super(transactionId, () -> {
            account.withdraw(amount);
        });
    }
}
