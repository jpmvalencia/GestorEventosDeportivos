package com.sportsevent.sportseventmanager.statistics;

import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.events.EventService;
import com.sportsevent.sportseventmanager.players.PlayerService;
import com.sportsevent.sportseventmanager.teams.TeamService;

import java.util.LinkedHashMap;
import java.util.Map;

public class StatisticService {
    PlayerService playerService;
    TeamService teamService;
    EventService eventService;

    public StatisticService(PlayerService playerService, TeamService teamService, EventService eventService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.eventService = eventService;
    }

    public SuccessResponse getStatistics() {
        double averagePlayersPerTeam = teamService.getAveragePlayersPerTeam();
        Map<String, Long> eventsBySport = eventService.countEventsBySport();
        Map<Integer, Long> teamsWithMostEvents = eventService.getTeamsWithMostEvents();
        Map<Integer, Double> occupancyPercentages = eventService.getOccupancyPercentages();

        Map<String, Object> statistics = new LinkedHashMap<>();
        statistics.put("averagePlayersPerTeam", averagePlayersPerTeam);
        statistics.put("eventsBySport", eventsBySport);
        statistics.put("teamsWithMostEvents", teamsWithMostEvents);
        statistics.put("occupancyPercentages", occupancyPercentages);

        return new SuccessResponse("Statistics retrieved successfully", 200, statistics);
    }
}
