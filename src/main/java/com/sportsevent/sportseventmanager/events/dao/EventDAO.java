package com.sportsevent.sportseventmanager.events.dao;

import com.sportsevent.sportseventmanager.events.model.Event;

import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    List<Event> events = new ArrayList<Event>();
    private static int idCounter = 1;
}
