package com.example.eventservice.service.impl;

import com.example.eventservice.model.Event;
import com.example.eventservice.repository.EventRepository;
import com.example.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long eventId, Event updatedEvent) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setCategory(updatedEvent.getCategory());
        event.setDate(updatedEvent.getDate());
        event.setLocation(updatedEvent.getLocation());
        return eventRepository.save(event);
    }
@Override
public void deleteEvent(Long eventId) {
	eventRepository.deleteById(eventId);
}
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

//    public Event bookmarkEvent(Long eventId, Long userId) {
//        Event event = eventRepository.findById(eventId).orElseThrow();
//        // Bookmark logic can go here
//        // Assuming the bookmarks are tracked by userId in the Event entity
//        return event;
//    }

//    public List<Event> getBookmarkedEvents(Long userId) {
//        // Fetch events bookmarked by the user
//        return eventRepository.findAll();  // Placeholder, adjust for actual bookmark tracking
//    }

	@Override
	public Event bookmarkEvent(Long eventId, String email) {
		// TODO Auto-generated method stub
		 Event event = eventRepository.findById(eventId).orElseThrow();
	        // Bookmark logic can go here
	        // Assuming the bookmarks are tracked by userId in the Event entity
	        return event;
	}

	@Override
	public List<Event> getBookmarkedEvents(String email) {
		// TODO Auto-generated method stub
		return eventRepository.findAll();
	}
}
