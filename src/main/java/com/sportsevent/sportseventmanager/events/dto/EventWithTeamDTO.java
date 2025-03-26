package com.sportsevent.sportseventmanager.events.dto;

import java.time.Instant;
import java.util.List;

public class EventWithTeamDTO {
    private int id;
    private String name;
    private Instant date;
    private String location;
    private String sport;
    private int capacity;
    private int ticketsSold;
    private String status;
    private List<String> teamNames; // Lista de nombres de equipos

    public EventWithTeamDTO(int id, String name, Instant date, String location, String sport, int capacity, int ticketsSold, String status, List<String> teamNames) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.sport = sport;
        this.capacity = capacity;
        this.ticketsSold = ticketsSold;
        this.status = status;
        this.teamNames = teamNames;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(List<String> teamNames) {
        this.teamNames = teamNames;
    }
}
