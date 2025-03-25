package com.sportsevent.sportseventmanager.teams;

import com.sportsevent.sportseventmanager.teams.dao.TeamDAO;
import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.List;

public class TeamRepository {
    private final TeamDAO teamDAO;

    public TeamRepository(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public List<Team> getTeams(int page, int size) {
        return teamDAO.getTeams(page, size);
    }

    public void addTeam(Team team) {
        teamDAO.addTeam(team);
    }

    public long getTotalRecords() {
        return teamDAO.getTotalRecords();
    }

    public boolean existsTeamByNameAndSport(String name, String sport) {
        return teamDAO.existsTeamByNameAndSport(name, sport);
    }
}
