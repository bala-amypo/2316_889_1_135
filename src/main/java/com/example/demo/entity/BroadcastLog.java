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

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.SENT;

    private Instant sentAt;

    @PrePersist
    public void onCreate() {
        this.sentAt = Instant.now();
    }

    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) {
        this.eventUpdate = eventUpdate;
    }

    public DeliveryStatus getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Instant getSentAt() { return sentAt; }
}
