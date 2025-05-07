package com.example.eventservice.service.impl;

import com.example.eventservice.model.RSVP;
import com.example.eventservice.repository.RSVPRepository;
import com.example.eventservice.service.RSVPService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RSVPServiceImpl implements RSVPService {

    public RSVPServiceImpl(RSVPRepository rsvpRepository) {
		super();
		this.rsvpRepository = rsvpRepository;
	}

	private final RSVPRepository rsvpRepository;

   
    @Override
    public List<RSVP> getRSVPsForEvent(Long eventId) {
        return rsvpRepository.findByEventId(eventId);
    }

    public List<RSVP> getRSVPsForUser(String email) {
        return rsvpRepository.findByEmail(email);
    }

	@Override
	public RSVP createRSVP(Long eventId, String email, boolean attending) {
		// TODO Auto-generated method stub
		RSVP rsvp = new RSVP(null, eventId, email, attending);
        return rsvpRepository.save(rsvp);
	}

	
}
