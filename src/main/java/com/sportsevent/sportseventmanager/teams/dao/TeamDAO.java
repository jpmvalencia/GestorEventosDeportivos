package com.sportsevent.sportseventmanager.teams.dao;

import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamDAO {
    private final List<Team> teams = new ArrayList<>();
    private static int idCounter = 1;

    public List<Team> getTeams(int page, int size) {
        int fromIndex = (page - 1) * size;

        if (fromIndex >= teams.size()) {
            return Collections.emptyList();
        }

        int toIndex = Math.min(fromIndex + size, teams.size());
        return teams.subList(fromIndex, toIndex);
    }

    public void addTeam(Team team) {
        team.setId(idCounter++);
        teams.addFirst(team);
    }

    public long getTotalRecords() {
        return teams.size();
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
