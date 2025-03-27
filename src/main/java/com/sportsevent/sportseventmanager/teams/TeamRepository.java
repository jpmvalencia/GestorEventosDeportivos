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

    public Team getTeamById(int teamId) {
        return teamDAO.getTeamById(teamId);
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

    public void addPlayerToTeam(int teamId, int playerId) {
        teamDAO.addPlayerToTeam(teamId, playerId);
    }

    public List<Team> getAllTeams() {
        return teamDAO.getAllTeams();
    }
}
