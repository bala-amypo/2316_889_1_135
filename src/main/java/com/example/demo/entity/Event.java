package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String location;

    private String category;

    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private User publisher;

    private LocalDateTime createdAt;

    public Event() {
    }

    public Event(Long id, String title, String description, String location, String category, User publisher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.publisher = publisher;
        this.isActive = true;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getLocation() {
        return location;
    }
 
    public void setLocation(String location) {
        this.location = location;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsActive() {
        return isActive;
    }
 
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public User getPublisher() {
        return publisher;
    }
 
    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
