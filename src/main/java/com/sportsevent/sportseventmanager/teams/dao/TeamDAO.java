package com.sportsevent.sportseventmanager.teams.dao;

import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private final List<Team> teams = new ArrayList<>();
    private static int idCounter = 1;

    public void addTeam(Team team) {
        team.setId(idCounter++);
        teams.addFirst(team);
    }

    public boolean existsTeamByNameAndSport(String teamName, String sport) {
        for (Team team : teams) {
            if (team.getName().equals(teamName) && team.getSport().equals(sport)) {
                return true;
            }
        }
        return false;
    }
}
