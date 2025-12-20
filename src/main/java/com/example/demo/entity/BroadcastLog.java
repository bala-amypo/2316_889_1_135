package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EventUpdate eventUpdate;

    @ManyToOne
    private User subscriber;

    private String deliveryStatus;
    private Instant sentAt;

    public BroadcastLog() {}

    public BroadcastLog(EventUpdate eventUpdate, User subscriber, String deliveryStatus) {
        this.eventUpdate = eventUpdate;
        this.subscriber = subscriber;
        this.deliveryStatus = deliveryStatus;
        this.sentAt = Instant.now();
    }
}
