package com.sportsevent.sportseventmanager.statistics;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.common.response.ErrorResponse;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.common.utils.GsonProvider;
import com.sportsevent.sportseventmanager.config.ServiceConfig;
import com.sportsevent.sportseventmanager.events.EventService;
import com.sportsevent.sportseventmanager.players.PlayerService;
import com.sportsevent.sportseventmanager.teams.TeamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StatisticServlet", urlPatterns = "/statistics")
public class StatisticServlet extends HttpServlet {
    PlayerService playerService;
    TeamService teamService;
    EventService eventService;
    StatisticService statisticService;
    Gson gson;

    @Override
    public void init() throws ServletException {
        playerService = ServiceConfig.getPlayerService();
        teamService = ServiceConfig.getTeamService();
        eventService = ServiceConfig.getEventService();
        statisticService = ServiceConfig.getStatisticService();

        gson = GsonProvider.createGson();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SuccessResponse successResponse = statisticService.getStatistics();

            String jsonSuccessResponse = gson.toJson(successResponse);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonSuccessResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        }
    }

}
