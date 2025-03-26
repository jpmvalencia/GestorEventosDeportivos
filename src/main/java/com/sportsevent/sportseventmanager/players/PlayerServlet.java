package com.sportsevent.sportseventmanager.players;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.common.exception.DuplicatePlayerException;
import com.sportsevent.sportseventmanager.common.exception.DuplicatePlayerNumberException;
import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.exception.TeamNotFoundException;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.ErrorResponse;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.common.utils.GsonProvider;
import com.sportsevent.sportseventmanager.common.validation.DTOValidator;
import com.sportsevent.sportseventmanager.config.ServiceConfig;
import com.sportsevent.sportseventmanager.players.dto.PlayerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PlayerServlet", urlPatterns = "/players")
public class PlayerServlet extends HttpServlet {
    private PlayerService playerService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        playerService = ServiceConfig.getPlayerService();
        gson = GsonProvider.createGson();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String sizeParam = req.getParameter("size");

        Integer page = (pageParam != null) ? Integer.parseInt(pageParam) : null;
        Integer size = (sizeParam != null) ? Integer.parseInt(sizeParam) : null;

        PaginationDTO paginationDTO = new PaginationDTO(page, size);

        try {
            DTOValidator.validate(paginationDTO);

            SuccessResponse successResponse = playerService.getPlayers(paginationDTO);

            String jsonSuccessResponse = gson.toJson(successResponse);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonSuccessResponse);
        } catch (InvalidDTOException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerDTO playerDTO = gson.fromJson(req.getReader(), PlayerDTO.class);

        try {
            DTOValidator.validate(playerDTO);

            SuccessResponse successResponse = playerService.addPlayer(playerDTO);
            String jsonSuccessResponse = gson.toJson(successResponse);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonSuccessResponse);
        } catch (TeamNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());

            String jsonErrorResponse = gson.toJson(errorResponse);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        } catch (DuplicatePlayerNumberException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        } catch (DuplicatePlayerException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonErrorResponse);
        }
    }
}
