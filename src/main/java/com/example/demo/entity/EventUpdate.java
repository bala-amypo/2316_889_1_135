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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }

    public String getUpdateType() { return updateType; }
    public void setUpdateType(String updateType) { this.updateType = updateType; }

    public LocalDateTime getTimestamp() { return postedAt; }
    public String getSeverityLevel() { return updateType; }
}
