package com.sportsevent.sportseventmanager.common.exception;

public class DuplicateEventException extends Exception {
    private final int errorCode;

    public DuplicateEventException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
