package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String category;
    private Boolean isActive = true;
    private Instant createdAt;

    @ManyToOne
    private User publisher;

    public Event() {}

    public Event(Long id, String title, String description, String location, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = Instant.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
