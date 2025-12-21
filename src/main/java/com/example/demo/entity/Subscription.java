package com.example.project.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class Subscription {
    @Id
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Event event;
    private Instant subscribedAt;

    public void onCreate() {
        this.subscribedAt = Instant.now();
    }

    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public Instant getSubscribedAt() { return subscribedAt; }

    public Subscription(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public Subscription() {
    }

    
}
