package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    private Instant subscribedAt;

    public Subscription() {}

    public Subscription(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    @PrePersist
    void onCreate() {
        this.subscribedAt = Instant.now();
    }

    // getters
    public Long getId() { return id; }
}
