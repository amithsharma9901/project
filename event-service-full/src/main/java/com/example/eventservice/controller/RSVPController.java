package com.example.eventservice.controller;

import com.example.eventservice.model.RSVP;
import com.example.eventservice.service.RSVPService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/rsvp")
@CrossOrigin(origins = "http://localhost:8081")

public class RSVPController {

    private final RSVPService rsvpService;

    public RSVPController(RSVPService rsvpService) {
        this.rsvpService = rsvpService;
    }

    /**
     * USER: RSVP to an Event
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{eventId}")
    public ResponseEntity<RSVP> rsvpEvent(@PathVariable Long eventId, @RequestParam boolean attending) {
        // Get the email from the authenticated user (no need for userId anymore)
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(401).body(null);  // Unauthorized if email is not found
        }
        
        // RSVP the event for the logged-in user
        return ResponseEntity.ok(rsvpService.createRSVP(eventId, email, attending));
    }

    /**
     * USER: Get all RSVPs for an Event
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{eventId}")
    public ResponseEntity<List<RSVP>> getRSVPsForEvent(@PathVariable Long eventId) {
        // Return the list of RSVPs for a given event
        return ResponseEntity.ok(rsvpService.getRSVPsForEvent(eventId));
    }

    /**
     * USER: Get all RSVPs for the authenticated User
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<List<RSVP>> getRSVPsForUser() {
        // Get the email from the authenticated user
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(401).body(null);  // Unauthorized if email is not found
        }

        // Return the list of RSVPs for the authenticated user
        return ResponseEntity.ok(rsvpService.getRSVPsForUser(email));
    }
}
