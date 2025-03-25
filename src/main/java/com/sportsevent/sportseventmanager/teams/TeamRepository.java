package com.sportsevent.sportseventmanager.teams;

import com.sportsevent.sportseventmanager.teams.dao.TeamDAO;
import com.sportsevent.sportseventmanager.teams.model.Team;

public class TeamRepository {
    private final TeamDAO teamDAO;

    public TeamRepository(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public void addTeam(Team team) {
        teamDAO.addTeam(team);
    }

    public boolean existsTeamByNameAndSport(String name, String sport) {
        return teamDAO.existsTeamByNameAndSport(name, sport);
    }
}
