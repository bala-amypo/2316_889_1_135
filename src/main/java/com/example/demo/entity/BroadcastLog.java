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
    private DeliveryStatus deliveryStatus;

    private Instant sentAt;

    @PrePersist
    public void onCreate() {
        if (deliveryStatus == null) {
            deliveryStatus = DeliveryStatus.SENT;
        }
        sentAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public EventUpdate getEventUpdate() {
        return eventUpdate;
    }

    public void setEventUpdate(EventUpdate eventUpdate) {
        this.eventUpdate = eventUpdate;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Instant getSentAt() {
        return sentAt;
    }
}
