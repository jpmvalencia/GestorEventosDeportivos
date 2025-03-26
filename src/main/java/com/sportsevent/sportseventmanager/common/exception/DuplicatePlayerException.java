package com.sportsevent.sportseventmanager.common.exception;

public class DuplicatePlayerException extends Exception {
    private final int errorCode;

    public DuplicatePlayerException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
