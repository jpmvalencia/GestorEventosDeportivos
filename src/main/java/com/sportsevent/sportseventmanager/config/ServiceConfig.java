package com.sportsevent.sportseventmanager.config;

import com.sportsevent.sportseventmanager.players.PlayerRepository;
import com.sportsevent.sportseventmanager.players.PlayerService;
import com.sportsevent.sportseventmanager.players.dao.PlayerDAO;
import com.sportsevent.sportseventmanager.teams.TeamRepository;
import com.sportsevent.sportseventmanager.teams.TeamService;
import com.sportsevent.sportseventmanager.teams.dao.TeamDAO;

public class ServiceConfig {
    private static PlayerDAO playerDAO;
    private static PlayerRepository playerRepository;
    private static TeamDAO teamDAO;
    private static TeamRepository teamRepository;
    private static PlayerService playerService;
    private static TeamService teamService;

    static {
        playerDAO = new PlayerDAO();
        teamDAO = new TeamDAO();

        playerRepository = new PlayerRepository(playerDAO);
        teamRepository = new TeamRepository(teamDAO);

        teamService = new TeamService(teamRepository);
        playerService = new PlayerService(playerRepository, teamService);
    }

    public static PlayerService getPlayerService() {
        return playerService;
    }

    public static TeamService getTeamService() {
        return teamService;
    }

    public static PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public static TeamDAO getTeamDAO() {
        return teamDAO;
    }
}
