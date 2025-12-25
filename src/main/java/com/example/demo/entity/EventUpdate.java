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

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    @PrePersist
    public void onCreate() {
        this.timestamp = LocalDateTime.now();
        if (this.severityLevel == null) {
            this.severityLevel = SeverityLevel.LOW;
        }
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }
}
