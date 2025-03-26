package com.sportsevent.sportseventmanager.events.dao;

import com.sportsevent.sportseventmanager.events.model.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventDAO {
    List<Event> events = new ArrayList<>();
    private static int idCounter = 1;

    public List<Event> getEvents(int page, int size) {
        int fromIndex = (page - 1) * size;

        if (fromIndex >= events.size()) {
            return Collections.emptyList();
        }

        int toIndex = Math.min(fromIndex + size, events.size());
        return events.subList(fromIndex, toIndex);
    }

    public Event getEventById(int eventId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }
        return null;
    }

    public long getTotalRecords() {
        return events.size();
    }

    public void addEvent(Event event) {
        event.setId(idCounter++);
        events.add(event);
    }

    public void addTeamToEvent(int eventId, int teamId) {
        Event event = getEventById(eventId);
        if (event != null) {
            event.addParticipatingTeam(teamId);
        }
    }

    public void updateStatusEvent(int eventId, String status) {
        Event event = getEventById(eventId);

        if (event != null) {
            event.setStatus(status);
        }
    }

    public boolean existsEventByNameAndSport(String eventName, String sport) {
        for (Event event : events) {
            if (event.getName().equals(eventName) && event.getSport().equals(sport)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTeamAlreadyAddedToEvent(int eventId, int teamId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event.getParticipatingTeams().contains(teamId);
            }
        }
        return false;
    }
}
