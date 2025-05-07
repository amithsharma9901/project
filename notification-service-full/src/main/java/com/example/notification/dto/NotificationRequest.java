package com.example.notification.dto;

public class NotificationRequest {
    private String email; // ✅ safer than userId
    private String title;
    private String message;
    private String type;
    private String toPhoneNumber;

    public NotificationRequest() {}

    public NotificationRequest(String email, String title, String message, String type, String toPhoneNumber) {
        this.email = email;
        this.title = title;
        this.message = message;
        this.type = type;
        this.toPhoneNumber = toPhoneNumber;
    }

    // Getters & Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }
}
