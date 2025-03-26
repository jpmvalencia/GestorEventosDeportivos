package com.sportsevent.sportseventmanager.common.exception;

public class InvalidEventStatusException extends Exception {
    private int errorCode;

    public InvalidEventStatusException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
