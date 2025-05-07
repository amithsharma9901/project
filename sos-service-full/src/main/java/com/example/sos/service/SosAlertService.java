package com.example.sos.service;

import com.example.sos.dto.SosAlertRequest;
import com.example.sos.model.SosAlert;
import com.example.sos.repository.SOSAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SosAlertService {

    private final SOSAlertRepository repository;
    private final TwilioService twilioService; // Twilio service for SMS alerts

    @Autowired
    public SosAlertService(SOSAlertRepository repository, TwilioService twilioService) {
        this.repository = repository;
        this.twilioService = twilioService;
    }

    public SosAlert sendSosAlert(SosAlertRequest request) {
        // Create a new SOS alert
        SosAlert sosAlert = new SosAlert();
        sosAlert.setUserId(request.getUserId());
        sosAlert.setMessage(request.getMessage());
        sosAlert.setAcknowledged(false);
        sosAlert.setTimestamp(LocalDateTime.now());
        sosAlert.setLatitude(request.getLatitude());  // Set latitude from request
        sosAlert.setLongitude(request.getLongitude());  // Set longitude from request

        // Save the SOS alert in the database
        SosAlert savedAlert = repository.save(sosAlert);

        // Send SMS alert via Twilio
        String smsMessage = "🚨 SOS Alert!\nUser ID: " + request.getUserId() +
                "\nMessage: " + request.getMessage() +
                "\nLocation: Lat " + request.getLatitude() + ", Long " + request.getLongitude();
        twilioService.sendSms(request.getToPhoneNumber(), smsMessage); // Directly send SMS to the provided phone number

        return savedAlert;
    }

    public List<SosAlert> getPendingAlerts() {
        return repository.findByAcknowledgedFalse();
    }

    public List<SosAlert> getHistory(Long userId) {
        return repository.findByUserId(userId);
    }

    public void acknowledgeAlert(Long id) {
        SosAlert sosAlert = repository.findById(id).orElseThrow();
        sosAlert.setAcknowledged(true);
        repository.save(sosAlert);
    }
}
