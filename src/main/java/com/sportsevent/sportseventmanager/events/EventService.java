package com.sportsevent.sportseventmanager.events;

import com.sportsevent.sportseventmanager.common.exception.*;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.events.dto.AddTeamToEventDTO;
import com.sportsevent.sportseventmanager.events.dto.EventDTO;
import com.sportsevent.sportseventmanager.events.dto.EventWithTeamDTO;
import com.sportsevent.sportseventmanager.events.dto.SellEventTicketDTO;
import com.sportsevent.sportseventmanager.events.model.Event;
import com.sportsevent.sportseventmanager.teams.TeamService;
import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<EventWithTeamDTO> eventsWithTeam = events.stream().map(event -> {
            List<String> teamNames = new ArrayList<>();

            try {
                for (Integer teamId : event.getParticipatingTeams()) {
                    Team team = teamService.getTeamById(teamId);

                    if (team != null) {
                        teamNames.add(team.getName());
                    }
                }
            } catch (TeamNotFoundException _) {
            }

            return new EventWithTeamDTO(
                    event.getId(),
                    event.getName(),
                    event.getDate(),
                    event.getLocation(),
                    event.getSport(),
                    event.getCapacity(),
                    event.getTicketsSold(),
                    event.getStatus(),
                    teamNames
            );
        }).collect(Collectors.toList());

        return new SuccessResponse(
                "events retrieved successfully",
                200,
                eventsWithTeam,
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

        if (event.getParticipatingTeams().size() >= 2) {
            eventRepository.updateStatusEvent(addTeamToEventDTO.getEventId(), "En Progreso");
        }

        return new SuccessResponse(
                "team added to event successfully",
                200,
                event
        );
    }

    public SuccessResponse updateEventStatus(int eventId, String status) throws EventNotFoundException, InvalidEventStatusException {
        Event event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new EventNotFoundException("event not found", 404);
        }

        if ("En Progreso".equals(status) && event.getParticipatingTeams().size() < 2) {
            throw new InvalidEventStatusException("cannot start the event with less than 2 teams", 400);
        }

        eventRepository.updateStatusEvent(eventId, status);

        return new SuccessResponse(
                "event status updated successfully",
                200,
                event
        );
    }

    public SuccessResponse sellTickets(int eventId, SellEventTicketDTO sellEventTicketDTO) throws EventNotFoundException, InsufficientTicketsException {
        Event event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new EventNotFoundException("event not found", 404);
        }

        int availableTickets = event.getCapacity() - event.getTicketsSold();

        if (sellEventTicketDTO.getQuantity() > availableTickets) {
            throw new InsufficientTicketsException("not enough tickets available", 409);
        }

        eventRepository.updateTicketsSold(eventId, event.getTicketsSold() + sellEventTicketDTO.getQuantity());

        return new SuccessResponse(
                "tickets sold succesfully",
                200,
                event
        );
    }
}
