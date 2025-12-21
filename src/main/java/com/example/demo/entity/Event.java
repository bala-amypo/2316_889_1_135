package com.example.project.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    private Long id;
    @ManyToOne
    private User publisher;
    private String title;
    private String description;
    private String location;
    private String category;
    private boolean isActive;
    private Instant createdAt;
    private Instant lastUpdatedAt;


    public void onCreate() {
        this.createdAt = Instant.now();
        this.lastUpdatedAt = Instant.now();
        this.isActive = true;
    }

    public void onUpdate() {
        this.lastUpdatedAt = Instant.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getPublisher() { return publisher; }
    public void setPublisher(User publisher) { this.publisher = publisher; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }

    public Instant getCreatedAt() { return createdAt; }
    public Instant getLastUpdatedAt() { return lastUpdatedAt; }
}
