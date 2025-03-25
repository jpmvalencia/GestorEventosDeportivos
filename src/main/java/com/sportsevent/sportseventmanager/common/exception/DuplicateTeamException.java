package com.sportsevent.sportseventmanager.common.exception;

public class DuplicateTeamException extends Exception {
    private final int errorCode;

    public DuplicateTeamException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
