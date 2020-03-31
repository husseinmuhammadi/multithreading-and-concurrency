package com.javastudio.tutorial.concurrency.account;

import ch.qos.logback.core.pattern.color.BlackCompositeConverter;
import com.javastudio.tutorial.concurrency.exception.TransactionNotAllowedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account {

    private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);

    private final String branch;
    private final String module;
    private final String scheme;
    private final String accountNo;

    private double overdraft = 0;

    private double balance;

    public Account(String branch, String module, String scheme, String accountNo) {
        this.branch = branch;
        this.module = module;
        this.scheme = scheme;
        this.accountNo = accountNo;
    }

    public String getAccountId() {
        return String.format("%s-%s-%s-%s", branch, module, scheme, accountNo);
    }

    public void withdraw(double amount) {
        LOGGER.info("Lock account {} for withdrawal ...", getAccountId());
        synchronized (this) {
            LOGGER.info("Validate account {} for withdrawal ...", getAccountId());
            if (balance - amount < overdraft)
                throw new TransactionNotAllowedException("Could not withdraw more then overdraft");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }

            LOGGER.info("Withdrawal account {} for withdrawal ...", getAccountId());
            balance -= amount;
            LOGGER.info("The balance for account {}: {}", getAccountId(), balance);
        }
        LOGGER.info("Release lock from account {}", getAccountId());
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
