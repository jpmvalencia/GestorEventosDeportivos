package com.sportsevent.sportseventmanager.common.exception;

public class PlayerNotFoundException extends Exception {
    private final int errorCode;

    public PlayerNotFoundException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
