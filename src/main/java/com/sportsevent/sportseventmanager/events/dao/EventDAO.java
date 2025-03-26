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

    public long getTotalRecords() {
        return events.size();
    }

    public void addEvent(Event event) {
        event.setId(idCounter++);
        events.add(event);
    }

    public boolean existsEventByNameAndSport(String eventName, String sport) {
        for (Event event : events) {
            if (event.getName().equals(eventName) && event.getSport().equals(sport)) {
                return true;
            }
        }
        return false;
    }
}
