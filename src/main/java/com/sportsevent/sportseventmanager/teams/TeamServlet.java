package com.sportsevent.sportseventmanager.teams;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.common.exception.DuplicateTeamException;
import com.sportsevent.sportseventmanager.common.exception.InvalidDTOException;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.ErrorResponse;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.common.validation.DTOValidator;
import com.sportsevent.sportseventmanager.teams.dao.TeamDAO;
import com.sportsevent.sportseventmanager.teams.dto.TeamDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String sizeParam = req.getParameter("size");

        Integer page = (pageParam != null) ? Integer.parseInt(pageParam) : null;
        Integer size = (sizeParam != null) ? Integer.parseInt(sizeParam) : null;

        PaginationDTO paginationDTO = new PaginationDTO(page, size);

        try {
            DTOValidator.validate(paginationDTO);

            SuccessResponse successResponse = teamService.getTeams(paginationDTO);

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

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamDTO teamDTO = gson.fromJson(req.getReader(), TeamDTO.class);

        try {
            DTOValidator.validate(teamDTO);

            SuccessResponse successResponse = teamService.addTeam(teamDTO);

            String jsonSuccessResponse = gson.toJson(successResponse);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonSuccessResponse);
        } catch (InvalidDTOException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonErrorResponse);
        } catch (DuplicateTeamException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonErrorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
            String jsonErrorResponse = gson.toJson(errorResponse);

            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonErrorResponse);
        }
    }
}
