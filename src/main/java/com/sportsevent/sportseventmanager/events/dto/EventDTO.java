package com.sportsevent.sportseventmanager.events.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

import java.time.Instant;

public class EventDTO implements Validatable {
    private String name;
    private Instant date;
    private String location;
    private int capacity;
    private String sport;

    public EventDTO(String name, Instant date, String location, int capacity, String sport) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", sport='" + sport + '\'' +
                '}';
    }

    @Override
    public void validate() throws InvalidDTOException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDTOException("name is required", 400);
        }

        if (date == null || date.isBefore(Instant.now())) {
            throw new InvalidDTOException("date must be a future date (after today)", 400);
        }

        if (location == null || location.trim().isEmpty()) {
            throw new InvalidDTOException("location is required", 400);
        }

        if (capacity <= 0) {
            throw new InvalidDTOException("capacity must be greater than 0", 400);
        }

        if (sport == null || sport.trim().isEmpty()) {
            throw new InvalidDTOException("sport is required", 400);
        }
    }
}
