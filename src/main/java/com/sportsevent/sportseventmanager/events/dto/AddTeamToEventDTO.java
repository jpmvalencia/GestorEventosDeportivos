package com.sportsevent.sportseventmanager.events.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

public class AddTeamToEventDTO implements Validatable {
    private Integer eventId;
    private Integer teamId;

    public AddTeamToEventDTO(Integer eventId, Integer teamId) {
        this.eventId = eventId;
        this.teamId = teamId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public void validate() throws InvalidDTOException {
        if (eventId == null || eventId <= 0) {
            throw new InvalidDTOException("eventId is required", 400);
        }

        if (teamId == null || teamId <= 0) {
            throw new InvalidDTOException("teamId is required", 400);
        }
    }
}
