package com.javastudio.tutorial.concurrency.exception;

public class TransactionNotAllowedException extends RuntimeException {
    public TransactionNotAllowedException() {
        super();
    }

    public TransactionNotAllowedException(String message) {
        super(message);
    }

    public TransactionNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotAllowedException(Throwable cause) {
        super(cause);
    }

    protected TransactionNotAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
