package com.sportsevent.sportseventmanager.events;

import com.sportsevent.sportseventmanager.common.exception.DuplicateEventException;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.events.dto.EventDTO;
import com.sportsevent.sportseventmanager.events.model.Event;

import java.util.List;

public class EventService {
    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public SuccessResponse getEvents(PaginationDTO paginationDTO) {
        int page = paginationDTO.getPage();
        int size = paginationDTO.getSize();

        List<Event> events = eventRepository.getEvents(page, size);
        long totalRecords = eventRepository.getTotalRecords();

        return new SuccessResponse(
                "events retrieved successfully",
                200,
                events,
                totalRecords
        );
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
