package com.taboo.exam.calc.exceptions;

public class UnsopportedOperationException extends RuntimeException  {
    private String operation;
    private String message = "Unsupported Operation encountered";

    public UnsopportedOperationException(String operation) {
        this.operation = operation;
    }

    public String getUnsupportedOperation() {
        return operation;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
