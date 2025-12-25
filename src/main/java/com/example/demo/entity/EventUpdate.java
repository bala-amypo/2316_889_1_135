package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    private String updateContent;

    private String updateType;

    private LocalDateTime postedAt;

    @PrePersist
    public void onCreate() {
        this.postedAt = LocalDateTime.now();
        if (this.updateType == null) {
            this.updateType = "INFO";
        }
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getTimestamp() {
        return postedAt;
    }

    public String getSeverityLevel() {
        return updateType;
    }
}
