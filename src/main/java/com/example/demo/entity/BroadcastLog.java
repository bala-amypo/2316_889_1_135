package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime sentAt;

    @PrePersist
    protected void onCreate() {
        this.sentAt = LocalDateTime.now();
        if (this.deliveryStatus == null) {
            this.deliveryStatus = "SENT";
        }
    }

    public Long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) { this.eventUpdate = eventUpdate; }

    public User getSubscriber() { return subscriber; }
    public void setSubscriber(User subscriber) { this.subscriber = subscriber; }

    public String getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(String deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public LocalDateTime getSentAt() { return sentAt; }
}
