package com.sportsevent.sportseventmanager.teams;

import com.sportsevent.sportseventmanager.teams.dao.TeamDAO;

public class TeamRepository {
    private TeamDAO teamDAO;

    public TeamRepository(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }
}
