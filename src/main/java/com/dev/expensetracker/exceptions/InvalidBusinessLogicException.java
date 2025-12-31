package com.dev.expensetracker.exceptions;

public class InvalidBusinessLogicException extends RuntimeException {
  public InvalidBusinessLogicException(String message) {
    super(message);
  }
}
