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

    public long getTotalRecords() {
        return eventDAO.getTotalRecords();
    }

    public boolean existsEventByNameAndSport(String eventName, String sport) {
        return eventDAO.existsEventByNameAndSport(eventName, sport);
    }

    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }
}
