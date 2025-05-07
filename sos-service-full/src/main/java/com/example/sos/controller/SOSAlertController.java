package com.example.sos.controller;

import com.example.sos.dto.SosAlertRequest;
import com.example.sos.model.SosAlert;
import com.example.sos.service.SosAlertService;
import com.example.sos.service.TwilioService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sos")
public class SOSAlertController {

    private final SosAlertService service;
    private final TwilioService twilioService;

    public SOSAlertController(SosAlertService service, TwilioService twilioService) {
        this.service = service;
        this.twilioService = twilioService;
    }

    // USER: Send SOS Alert
    @PostMapping("/send")
    @PreAuthorize("hasRole('USER')")
    public SosAlert send(@RequestBody SosAlertRequest request) {
        SosAlert alert = service.sendSosAlert(request);

        String messageBody = "🚨 SOS Alert: " + request.getMessage();
        twilioService.sendSms(request.getToPhoneNumber(), messageBody);

        return alert;
    }

    // ADMIN: View pending alerts
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SosAlert> getPendingAlerts() {
        return service.getPendingAlerts();
    }

    // USER or ADMIN: View alert history
    @GetMapping("/history/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<SosAlert> getHistory(@PathVariable Long userId) {
        return service.getHistory(userId);
    }

    // ADMIN: Acknowledge alert
    @PostMapping("/acknowledge/{sosId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String acknowledgeAlert(@PathVariable Long sosId) {
        service.acknowledgeAlert(sosId);
        return "SOS Alert acknowledged";
    }
}
