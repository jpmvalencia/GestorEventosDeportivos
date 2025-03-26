package com.sportsevent.sportseventmanager.players.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

import java.time.Instant;

public class PlayerDTO implements Validatable {
    private String firstName;
    private String lastName;
    private Instant birthDate;
    private String nationality;
    private int number;
    private int teamId;

    public PlayerDTO(String firstName, String lastName, Instant birthDate, String nationality, int number, int teamId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.number = number;
        this.teamId = teamId;
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

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", number=" + number +
                ", teamId=" + teamId +
                '}';
    }


    @Override
    public void validate() throws InvalidDTOException {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidDTOException("firstName is required", 400);
        }

        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidDTOException("lastName is required", 400);
        }

        if (birthDate == null) {
            throw new InvalidDTOException("birthDate is required", 400);
        }

        if (nationality == null || nationality.trim().isEmpty()) {
            throw new InvalidDTOException("nationality is required", 400);
        }

        if (number < 0 || number > 100) {
            throw new InvalidDTOException("number must be between 0 and 100", 400);
        }

        if (teamId < 0) {
            throw new InvalidDTOException("teamId is required", 400);
        }
    }
}
