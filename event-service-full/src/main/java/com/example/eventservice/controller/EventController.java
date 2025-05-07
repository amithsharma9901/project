package com.example.eventservice.controller;

import com.example.eventservice.model.Event;
import com.example.eventservice.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:8081")
public class EventController {

    private final EventService eventService;
    private final RestTemplate restTemplate;

    // Optional: User Management Service URL
    private final String userManagementServiceUrl = "http://localhost:8087";

    public EventController(EventService eventService, RestTemplate restTemplate) {
        this.eventService = eventService;
        this.restTemplate = restTemplate;
    }

    /**
     * ADMIN: Create a new event
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    /**
     * ADMIN: Update an existing event
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, event));
    }

    /**
     * ADMIN: Delete an event
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully");
    }

    /**
     * PUBLIC: Get all available events
     */
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    /**
     * USER: Bookmark an event
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/bookmark/{eventId}")
    public ResponseEntity<?> bookmarkEvent(@PathVariable Long eventId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(401).body("Unauthorized: No user found in context");
        }

        return ResponseEntity.ok(eventService.bookmarkEvent(eventId, email));
    }

    /**
     * USER: Get all bookmarked events for the authenticated user
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/bookmarked")
    public ResponseEntity<?> getBookmarkedEvents() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(401).body("Unauthorized: No user found in context");
        }

        return ResponseEntity.ok(eventService.getBookmarkedEvents(email));
    }
}
