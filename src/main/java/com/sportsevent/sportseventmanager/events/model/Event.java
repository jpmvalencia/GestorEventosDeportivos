package com.sportsevent.sportseventmanager.events.model;

import java.time.Instant;
import java.util.ArrayList;

public class Event {
    private int id;
    private String name;
    private Instant date;
    private String location;
    private String sport;
    private ArrayList<Integer> participatingTeams;
    private int capacity;
    private int ticketsSold;
    private String status;

    public Event(String name, Instant date, String location, String sport, int capacity, int ticketsSold) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.sport = sport;
        this.participatingTeams = new ArrayList<>();
        this.capacity = capacity;
        this.ticketsSold = ticketsSold;
        this.status = "Programado";
    }

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

    public ArrayList<Integer> getParticipatingTeams() {
        return participatingTeams;
    }

    public void setParticipatingTeams(ArrayList<Integer> participatingTeams) {
        this.participatingTeams = participatingTeams;
    }

    public void addParticipatingTeam(int teamId) {
        participatingTeams.add(teamId);
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
}
