package com.example.eventservice.repository;

import com.example.eventservice.model.RSVP;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RSVPRepository extends JpaRepository<RSVP, Long> {
    List<RSVP> findByEventId(Long eventId);

	List<RSVP> findByEmail(String email);

	//List<RSVP> findByEmail(String email);

    
}
