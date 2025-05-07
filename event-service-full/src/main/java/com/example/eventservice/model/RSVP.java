package com.example.eventservice.model;

import jakarta.persistence.*;

@Entity
public class RSVP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private String email;
    private boolean attending;

    public RSVP() {}

	public RSVP(Long id, Long eventId, String email, boolean attending) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.email = email;
		this.attending = attending;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAttending() {
		return attending;
	}

	public void setAttending(boolean attending) {
		this.attending = attending;
	}

    // Getters and setters
}