package com.sportsevent.sportseventmanager.common.exception;

public class InsufficientTicketsException extends Exception {
    private final int errorCode;

    public InsufficientTicketsException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
