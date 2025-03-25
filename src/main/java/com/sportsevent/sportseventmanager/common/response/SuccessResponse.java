package com.sportsevent.sportseventmanager.common.response;

import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.List;

public class SuccessResponse {
    private String message;
    private int status;
    private List<Team> data;
    private Long totalRecords;

    public SuccessResponse(String message, int status, List<Team> data, Long totalRecords) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.totalRecords = totalRecords;
    }

    public SuccessResponse(String message, int status, Team data) {
        this.message = message;
        this.status = status;
        this.data = List.of(data);
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

    public List<Team> getData() {
        return data;
    }

    public void setData(List<Team> data) {
        this.data = data;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }
}
