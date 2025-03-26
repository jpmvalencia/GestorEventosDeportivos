package com.sportsevent.sportseventmanager.events;

public class EventService {
    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
