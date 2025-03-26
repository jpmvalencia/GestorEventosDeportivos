package com.sportsevent.sportseventmanager.config;

import com.sportsevent.sportseventmanager.events.EventRepository;
import com.sportsevent.sportseventmanager.events.EventService;
import com.sportsevent.sportseventmanager.events.dao.EventDAO;
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
    private static EventDAO eventDAO;
    private static EventRepository eventRepository;
    private static PlayerService playerService;
    private static TeamService teamService;
    private static EventService eventService;

    static {
        playerDAO = new PlayerDAO();
        teamDAO = new TeamDAO();
        eventDAO = new EventDAO();

        playerRepository = new PlayerRepository(playerDAO);
        teamRepository = new TeamRepository(teamDAO);
        eventRepository = new EventRepository(eventDAO);

        teamService = new TeamService(teamRepository);
        playerService = new PlayerService(playerRepository, teamService);
        eventService = new EventService(eventRepository);
    }

    public static PlayerService getPlayerService() {
        return playerService;
    }

    public static TeamService getTeamService() {
        return teamService;
    }

    public static EventService getEventService() {
        return eventService;
    }

    public static PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public static TeamDAO getTeamDAO() {
        return teamDAO;
    }
    
    public static EventDAO getEventDAO() {
        return eventDAO;
    }


}
