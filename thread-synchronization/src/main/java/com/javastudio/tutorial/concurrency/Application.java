package com.javastudio.tutorial.concurrency;

import com.javastudio.tutorial.concurrency.account.Account;
import com.javastudio.tutorial.concurrency.transaction.Withdrawal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Account account1 = new Account("1001", "10", "800", "00000001");
        Account account2 = new Account("1001", "10", "800", "00000002");

        account1.deposit(10);
        account2.deposit(10);

        new Withdrawal("T1", account1, 8).start();
        new Withdrawal("T2", account1, 9).start();
        new Withdrawal("T3", account2, 7).start();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
