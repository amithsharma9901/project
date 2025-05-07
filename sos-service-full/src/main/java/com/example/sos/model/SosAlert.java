package com.example.sos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SosAlert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private String message;
    private boolean acknowledged;
    private LocalDateTime timestamp;
    private double latitude;  // Added latitude field
    private double longitude; // Added longitude field
    
    // No-arg constructor for JPA
    public SosAlert() {}

    // All-args constructor
    public SosAlert(Long id, Long userId, String message, boolean acknowledged, LocalDateTime timestamp, double latitude, double longitude) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.acknowledged = acknowledged;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
