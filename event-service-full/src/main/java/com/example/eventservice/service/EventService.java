package com.example.eventservice.service;

import com.example.eventservice.model.Event;
import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Long eventId, Event event);
    List<Event> getAllEvents();
    Event bookmarkEvent(Long eventId, String email);
    List<Event> getBookmarkedEvents(String email);
	void deleteEvent(Long eventId);
}
