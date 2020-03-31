package com.javastudio.tutorial.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferWithThreadDeadLock extends Transaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferWithThreadDeadLock.class);

    TransferWithThreadDeadLock(String id, Account from, Account to, double amount) {
        super(id, from, to, amount);
    }

    @Override
    public void run() {
        // Acquire the lock of Account 'from'
        LOGGER.info("Locking account {} for withdrawal ...", from);
        synchronized (from) {
            LOGGER.info("Withdrawing account {} ...", from);
            from.withdraw(amount);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }

            LOGGER.info("Locking account {} for deposit ...", to);
            // Acquire the lock of Account 'to'
            synchronized (to) {
                LOGGER.info("Depositing account {} ...", to);
                to.deposit(amount);
            }
            // Release the lock of Account 'to'
            LOGGER.info("Lock released from account {} ...", to);
        }
        // Release the lock of Account 'from'
        LOGGER.info("Lock released from account {} ...", from);

        LOGGER.info("{} is transferred from {} to {}", amount, from, to);
    }
}
