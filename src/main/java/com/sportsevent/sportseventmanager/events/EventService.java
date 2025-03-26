package com.sportsevent.sportseventmanager.events;

import com.sportsevent.sportseventmanager.common.exception.DuplicateEventException;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.events.dto.EventDTO;
import com.sportsevent.sportseventmanager.events.model.Event;

public class EventService {
    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public SuccessResponse addEvent(EventDTO eventDTO) throws DuplicateEventException {
        Event event = new Event(
                eventDTO.getName(),
                eventDTO.getDate(),
                eventDTO.getLocation(),
                eventDTO.getSport(),
                eventDTO.getCapacity()
        );

        if (eventRepository.existsEventByNameAndSport(event.getName(), event.getSport())) {
            throw new DuplicateEventException("event with the same name and sport already exists", 409);
        }

        eventRepository.addEvent(event);

        return new SuccessResponse(
                "event created successfully",
                201,
                event
        );
    }
}
