package com.sportsevent.sportseventmanager.events;

import com.sportsevent.sportseventmanager.common.exception.DuplicateEventException;
import com.sportsevent.sportseventmanager.common.exception.EventNotFoundException;
import com.sportsevent.sportseventmanager.common.exception.TeamAlreadyAddedException;
import com.sportsevent.sportseventmanager.common.exception.TeamNotFoundException;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.events.dto.AddTeamToEventDTO;
import com.sportsevent.sportseventmanager.events.dto.EventDTO;
import com.sportsevent.sportseventmanager.events.model.Event;
import com.sportsevent.sportseventmanager.teams.TeamService;

import java.util.List;

public class EventService {
    EventRepository eventRepository;
    TeamService teamService;

    public EventService(EventRepository eventRepository, TeamService teamService) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
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

    public SuccessResponse addTeamToEvent(AddTeamToEventDTO addTeamToEventDTO) throws EventNotFoundException, TeamAlreadyAddedException, TeamNotFoundException {
        Event event = eventRepository.getEventById(addTeamToEventDTO.getEventId());

        if (event == null) {
            throw new EventNotFoundException("event not found", 404);
        }

        if (eventRepository.isTeamAlreadyAddedToEvent(addTeamToEventDTO.getEventId(), addTeamToEventDTO.getTeamId())) {
            throw new TeamAlreadyAddedException("team already added", 409);
        }

        teamService.getTeamById(addTeamToEventDTO.getTeamId());
        
        eventRepository.addTeamToEvent(addTeamToEventDTO.getEventId(), addTeamToEventDTO.getTeamId());

        return new SuccessResponse(
                "team added to event successfully",
                200,
                event
        );
    }
}
