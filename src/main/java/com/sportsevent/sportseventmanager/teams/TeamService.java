package com.sportsevent.sportseventmanager.teams;

public class TeamService {
    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
}
