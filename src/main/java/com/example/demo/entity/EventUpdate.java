package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String updateContent;
    private String updateType;
    private Instant postedAt;

    @ManyToOne
    private Event event;

    public EventUpdate() {}

    public EventUpdate(Long id, String updateContent, String updateType) {
        this.id = id;
        this.updateContent = updateContent;
        this.updateType = updateType;
    }

    @PrePersist
    void onCreate() {
        this.postedAt = Instant.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}
