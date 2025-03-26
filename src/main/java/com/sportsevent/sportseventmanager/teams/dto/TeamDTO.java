package com.sportsevent.sportseventmanager.teams.dto;

import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.validation.Validatable;

public class TeamDTO implements Validatable {
    private String name;
    private String sport;
    private String city;
    private String foundationDate;
    private String logo;

    public TeamDTO(String name, String sport, String city, String foundationDate, String logo) {
        this.name = name;
        this.sport = sport;
        this.city = city;
        this.foundationDate = foundationDate;
        this.logo = logo;
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

    @Override
    public String toString() {
        return "TeamDTO{" +
                "name='" + name + '\'' +
                ", sport='" + sport + '\'' +
                ", city='" + city + '\'' +
                ", foundationDate='" + foundationDate + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    @Override
    public void validate() throws InvalidDTOException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDTOException("team name is required", 400);
        }

        if (sport == null || sport.trim().isEmpty()) {
            throw new InvalidDTOException("team sport is required", 400);
        }

        if (city == null || city.trim().isEmpty()) {
            throw new InvalidDTOException("team city is required", 400);
        }

        if (foundationDate == null || foundationDate.trim().isEmpty()) {
            throw new InvalidDTOException("team foundationDate is required", 400);
        }

        if (logo == null || logo.trim().isEmpty()) {
            throw new InvalidDTOException("team logo is required", 400);
        }
    }
}
