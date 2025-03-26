package com.sportsevent.sportseventmanager.events;

import com.google.gson.Gson;
import com.sportsevent.sportseventmanager.common.utils.GsonProvider;
import com.sportsevent.sportseventmanager.config.ServiceConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "EventServlet", urlPatterns = "/events")
public class EventServlet extends HttpServlet {
    EventService eventService;
    Gson gson;

    @Override
    public void init() throws ServletException {
        eventService = ServiceConfig.getEventService();
        gson = GsonProvider.createGson();
    }
}
