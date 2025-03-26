package com.sportsevent.sportseventmanager.events;

import com.sportsevent.sportseventmanager.events.dao.EventDAO;

public class EventRepository {
    EventDAO eventDAO;

    public EventRepository(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }
}
