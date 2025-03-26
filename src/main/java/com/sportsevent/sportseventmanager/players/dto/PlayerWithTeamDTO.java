package com.sportsevent.sportseventmanager.players.dto;

import java.time.Instant;

public class PlayerWithTeamDTO {
    private int playerId;
    private String firstName;
    private String lastName;
    private Instant birthDate;
    private String nationality;
    private int number;
    private int teamId;
    private String teamName;

    public PlayerWithTeamDTO(int playerId, String firstName, String lastName, Instant birthDate, String nationality, int number, int teamId, String teamName) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.number = number;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
