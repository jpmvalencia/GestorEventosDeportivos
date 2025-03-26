package com.sportsevent.sportseventmanager.teams.dto;

import java.util.List;

public class TeamWithPlayerDTO {
    private int id;
    private String name;
    private String sport;
    private String city;
    private String foundationDate;
    private String logo;
    private List<String> playerNames;

    public TeamWithPlayerDTO(int id, String name, String sport, String city, String foundationDate, String logo, List<String> playerNames) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.city = city;
        this.foundationDate = foundationDate;
        this.logo = logo;
        this.playerNames = playerNames;
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

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(String foundationDate) {
        this.foundationDate = foundationDate;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }
}
