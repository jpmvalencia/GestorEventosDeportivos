package com.sportsevent.sportseventmanager.common.exception;

public class EventNotFoundException extends Exception {
    private final int errorCode;

    public EventNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
