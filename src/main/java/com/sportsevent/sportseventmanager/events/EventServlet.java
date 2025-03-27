package com.sportsevent.sportseventmanager.events;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.common.exception.*;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.ErrorResponse;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.common.utils.GsonProvider;
import com.sportsevent.sportseventmanager.common.validation.DTOValidator;
import com.sportsevent.sportseventmanager.config.ServiceConfig;
import com.sportsevent.sportseventmanager.events.dto.AddTeamToEventDTO;
import com.sportsevent.sportseventmanager.events.dto.ChangeEventStatusDTO;
import com.sportsevent.sportseventmanager.events.dto.EventDTO;
import com.sportsevent.sportseventmanager.events.dto.SellEventTicketDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "EventServlet", urlPatterns = "/events/*")
public class EventServlet extends HttpServlet {
    EventService eventService;
    Gson gson;

    @Override
    public void init() throws ServletException {
        eventService = ServiceConfig.getEventService();
        gson = GsonProvider.createGson();
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

            SuccessResponse successResponse = eventService.getEvents(paginationDTO);

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
        EventDTO eventDTO = gson.fromJson(req.getReader(), EventDTO.class);

        try {
            DTOValidator.validate(eventDTO);

            SuccessResponse successResponse = eventService.addEvent(eventDTO);

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
        } catch (DuplicateEventException e) {
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

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {

            AddTeamToEventDTO addTeamToEventDTO = gson.fromJson(req.getReader(), AddTeamToEventDTO.class);

            try {
                DTOValidator.validate(addTeamToEventDTO);

                SuccessResponse successResponse = eventService.addTeamToEvent(addTeamToEventDTO);

                String jsonSuccessResponse = gson.toJson(successResponse);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonSuccessResponse);
            } catch (TeamNotFoundException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());

                String jsonErrorResponse = gson.toJson(errorResponse);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonErrorResponse);
            } catch (InvalidDTOException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonErrorResponse);
            } catch (EventNotFoundException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonErrorResponse);
            } catch (TeamAlreadyAddedException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonErrorResponse);
            } catch (Exception e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonErrorResponse);
            }
        } else if (pathInfo.matches("/\\d+/status")) {
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length != 3) {
                ErrorResponse errorResponse = new ErrorResponse("invalid path parameters", 500);
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonErrorResponse);
            }

            int eventId = Integer.parseInt(pathParts[1]);

            ChangeEventStatusDTO changeEventStatusDTO = gson.fromJson(req.getReader(), ChangeEventStatusDTO.class);


            try {
                DTOValidator.validate(changeEventStatusDTO);

                SuccessResponse successResponse = eventService.updateEventStatus(eventId, changeEventStatusDTO.getStatus());
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
            } catch (EventNotFoundException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonErrorResponse);
            } catch (InvalidEventStatusException e) {
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
        } else if (pathInfo.matches("/\\d+/sell-ticket")) {
            String[] pathParts = pathInfo.split("/");

            if (pathParts.length != 3) {
                ErrorResponse errorResponse = new ErrorResponse("invalid path parameters", 500);
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(jsonErrorResponse);
                return;
            }

            int eventId = Integer.parseInt(pathParts[1]);
            SellEventTicketDTO sellEventTicketDTO = gson.fromJson(req.getReader(), SellEventTicketDTO.class);

            try {
                DTOValidator.validate(sellEventTicketDTO);

                SuccessResponse successResponse = eventService.sellTickets(eventId, sellEventTicketDTO);

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(gson.toJson(successResponse));

            } catch (EventNotFoundException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.setContentType("application/json");
                resp.getWriter().write(jsonErrorResponse);

            } catch (InsufficientTicketsException e) {
                ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getErrorCode());
                String jsonErrorResponse = gson.toJson(errorResponse);

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
}