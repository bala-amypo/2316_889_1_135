package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EventUpdate eventUpdate;

    @ManyToOne
    private User subscriber;

    private String deliveryStatus; // PENDING / SENT / FAILED

    private Instant sentAt;

    @PrePersist
    public void onCreate() {
        this.sentAt = Instant.now();
        if (this.deliveryStatus == null) {
            this.deliveryStatus = "SENT";
        }
    }

    /* getters & setters */

    public Long getId() { return id; }

    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) { this.eventUpdate = eventUpdate; }

    public User getSubscriber() { return subscriber; }
    public void setSubscriber(User subscriber) { this.subscriber = subscriber; }

    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public Instant getSentAt() { return sentAt; }
}
