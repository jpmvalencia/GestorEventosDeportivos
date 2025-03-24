package com.sportsevent.sportseventmanager.teams;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.teams.dao.TeamDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "TeamServlet", urlPatterns = "/teams")
public class TeamServlet extends HttpServlet {
    private TeamService teamService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        TeamDAO teamDAO = new TeamDAO();
        TeamRepository teamRepository = new TeamRepository(teamDAO);
        teamService = new TeamService(teamRepository);

        gson = new Gson();
    }
}
