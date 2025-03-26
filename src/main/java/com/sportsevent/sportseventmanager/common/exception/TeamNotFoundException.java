package com.sportsevent.sportseventmanager.common.exception;

public class TeamNotFoundException extends Exception {
    private final int errorCode;

    public TeamNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
