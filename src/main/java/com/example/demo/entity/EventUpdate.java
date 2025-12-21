package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_updates")
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String updateContent;

    private String updateType; // INFO / WARNING / CRITICAL

    private LocalDateTime postedAt;

    public EventUpdate() {
    }

    public EventUpdate(Long id, Event event, String updateContent, String updateType) {
        this.id = id;
        this.event = event;
        this.updateContent = updateContent;
        this.updateType = updateType;
    }

    @PrePersist
    public void onCreate() {
        this.postedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }
 
    public void setEvent(Event event) {
        this.event = event;
    }

    public String getUpdateContent() {
        return updateContent;
    }
 
    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getUpdateType() {
        return updateType;
    }
 
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }
}
