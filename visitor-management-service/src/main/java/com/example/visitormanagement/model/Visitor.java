package com.example.visitormanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String purpose;
    private Long staffId;
    private Long userId;
    private LocalDateTime visitTime;

    public Visitor() {}

    public Visitor(String name, String purpose, Long staffId, Long userId, LocalDateTime visitTime) {
        this.name = name;
        this.purpose = purpose;
        this.staffId = staffId;
        this.userId = userId;
        this.visitTime = visitTime;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalDateTime getVisitTime() { return visitTime; }
    public void setVisitTime(LocalDateTime visitTime) { this.visitTime = visitTime; }
}
