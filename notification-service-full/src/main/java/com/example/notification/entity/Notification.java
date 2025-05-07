package com.example.notification.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private String title;
	private String message;
	private boolean seen;
	private String type;
	private LocalDateTime timestamp;

	// Required no-arg constructor for JPA
	public Notification() {}

	// All-args constructor
	public Notification(Long id, Long userId, String title, String message, boolean seen, String type, LocalDateTime timestamp) {
	    this.id = id;
	    this.userId = userId;
	    this.title = title;
	    this.message = message;
	    this.seen = seen;
	    this.type = type;
	    this.timestamp = timestamp;
	}

	// Builder pattern implementation
	public static class Builder {
	    private Long id;
	    private Long userId;
	    private String title;
	    private String message;
	    private boolean seen;
	    private String type;
	    private LocalDateTime timestamp;

	    public Builder() {}

	    public Builder id(Long id) {
	        this.id = id;
	        return this;
	    }

	    public Builder userId(Long userId) {
	        this.userId = userId;
	        return this;
	    }

	    public Builder title(String title) {
	        this.title = title;
	        return this;
	    }

	    public Builder message(String message) {
	        this.message = message;
	        return this;
	    }

	    public Builder seen(boolean seen) {
	        this.seen = seen;
	        return this;
	    }

	    public Builder type(String type) {
	        this.type = type;
	        return this;
	    }

	    public Builder timestamp(LocalDateTime timestamp) {
	        this.timestamp = timestamp;
	        return this;
	    }

	    public Notification build() {
	        return new Notification(id, userId, title, message, seen, type, timestamp);
	    }
	}

	public static Builder builder() {
	    return new Builder();
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

	public boolean isSeen() {
	    return seen;
	}

	public void setSeen(boolean seen) {
	    this.seen = seen;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public LocalDateTime getTimestamp() {
	    return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
	    this.timestamp = timestamp;
	}
}