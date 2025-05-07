package com.example.sos.dto;

public class SosAlertRequest {
    private Long userId;
    private String message;
    private double latitude;  // Added latitude field
    private double longitude; // Added longitude field
    private String toPhoneNumber; // Added phone number field for Twilio SMS

    // Constructor, getters, and setters
    public SosAlertRequest() {}

    public SosAlertRequest(Long userId, String message, double latitude, double longitude, String toPhoneNumber) {
        this.userId = userId;
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
        this.toPhoneNumber = toPhoneNumber;
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

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }
}
