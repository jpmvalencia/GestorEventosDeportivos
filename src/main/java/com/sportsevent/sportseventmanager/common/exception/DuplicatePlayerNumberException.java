package com.sportsevent.sportseventmanager.common.exception;

public class DuplicatePlayerNumberException extends Exception {
    private final int errorCode;

    public DuplicatePlayerNumberException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
