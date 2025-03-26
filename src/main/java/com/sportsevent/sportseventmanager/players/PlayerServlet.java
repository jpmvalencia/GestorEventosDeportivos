package com.sportsevent.sportseventmanager.players;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.players.dao.PlayerDAO;
import com.sportsevent.sportseventmanager.common.utils.GsonProvider;
import com.sportsevent.sportseventmanager.config.ServiceConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "PlayerServlet", urlPatterns = "/players")
public class PlayerServlet extends HttpServlet {
    private PlayerService playerService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerRepository playerRepository = new PlayerRepository(playerDAO);
        playerService = new PlayerService(playerRepository);
        playerService = ServiceConfig.getPlayerService();
        gson = GsonProvider.createGson();
    }

        gson = new Gson();
    }
}
