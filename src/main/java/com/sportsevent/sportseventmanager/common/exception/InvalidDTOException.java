package com.sportsevent.sportseventmanager.common.exception;

public class InvalidDTOException extends Exception {
    private final int errorCode;

    public InvalidDTOException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
