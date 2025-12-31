package com.dev.expensetracker.common.exceptions;

public class InvalidBusinessLogicException extends RuntimeException {
    public InvalidBusinessLogicException(String message) {
        super(message);
    }
}
