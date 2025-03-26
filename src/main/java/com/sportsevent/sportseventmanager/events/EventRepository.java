package com.sportsevent.sportseventmanager.events;

import com.sportsevent.sportseventmanager.events.dao.EventDAO;
import com.sportsevent.sportseventmanager.events.model.Event;

import java.util.List;

public class EventRepository {
    EventDAO eventDAO;

    public EventRepository(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public List<Event> getEvents(int page, int size) {
        return eventDAO.getEvents(page, size);
    }

    public Event getEventById(int id) {
        return eventDAO.getEventById(id);
    }

    public long getTotalRecords() {
        return eventDAO.getTotalRecords();
    }

    public boolean existsEventByNameAndSport(String eventName, String sport) {
        return eventDAO.existsEventByNameAndSport(eventName, sport);
    }

    public boolean isTeamAlreadyAddedToEvent(int eventId, int teamId) {
        return eventDAO.isTeamAlreadyAddedToEvent(eventId, teamId);
    }

    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }

    public void addTeamToEvent(int teamId, int eventId) {
        eventDAO.addTeamToEvent(teamId, eventId);
    }
}
