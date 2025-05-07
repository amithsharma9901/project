package com.example.eventservice.service;

import com.example.eventservice.model.RSVP;

import java.util.List;

public interface RSVPService {

    RSVP createRSVP(Long eventId, String email, boolean attending);

    List<RSVP> getRSVPsForEvent(Long eventId);

    List<RSVP> getRSVPsForUser(String email);
}
