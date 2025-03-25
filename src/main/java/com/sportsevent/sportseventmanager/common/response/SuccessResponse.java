package com.sportsevent.sportseventmanager.common.response;

import com.sportsevent.sportseventmanager.teams.model.Team;

public class SuccessResponse {
    private String message;
    private int status;
    private Team data;

    public SuccessResponse(String message, int status, Team data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Team getData() {
        return data;
    }

    public void setData(Team data) {
        this.data = data;
    }
}
