package com.example.notification.controller;

import com.example.notification.entity.Notification;
import com.example.notification.service.NotificationService;
import com.example.notification.service.TwilioService;
import com.example.notification.dto.NotificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:8081")
public class NotificationController {

    private final NotificationService service;
    private final TwilioService twilioService;

    public NotificationController(NotificationService service, TwilioService twilioService) {
        this.service = service;
        this.twilioService = twilioService;
    }

    // 🔒 ADMIN: Send notification
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/send")
    public ResponseEntity<Notification> send(@RequestBody NotificationRequest request) {
        try {
            Notification notification = service.sendNotification(request);

            // 🔔 Send SMS
            String messageBody = "📢 " + request.getTitle() + ": " + request.getMessage();
            if (request.getToPhoneNumber() != null && !request.getToPhoneNumber().isEmpty()) {
                twilioService.sendSms(request.getToPhoneNumber(), messageBody);
            }

            return new ResponseEntity<>(notification, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 🔒 USER: Get unread notifications
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/unread/{userId}")
    public ResponseEntity<List<Notification>> getUnread(@PathVariable Long userId) {
        List<Notification> unreadNotifications = service.getUnread(userId);
        return unreadNotifications.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(unreadNotifications, HttpStatus.OK);
    }

    // 🔒 USER: Mark notification as seen
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/mark-seen/{id}")
    public ResponseEntity<String> markSeen(@PathVariable Long id) {
        if (service.markSeen(id)) {
            return ResponseEntity.ok("Marked as seen");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
        }
    }

    // 🔒 USER or ADMIN: Get notification history
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Notification>> getHistory(@PathVariable Long userId) {
        List<Notification> history = service.getHistory(userId);
        return history.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(history, HttpStatus.OK);
    }

    // 🔒 ADMIN: Delete notification by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable Long id) {
        boolean deleted = service.deleteNotification(id);
        return deleted
                ? ResponseEntity.ok("Notification deleted")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notification not found");
    }

    // 🔒 ADMIN: Get all notifications
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> all = service.getAllNotifications();
        return all.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(all, HttpStatus.OK);
    }
}
