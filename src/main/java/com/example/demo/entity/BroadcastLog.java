package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_update_id")
    private EventUpdate eventUpdate;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    private String deliveryStatus; // PENDING / SENT / FAILED

    private LocalDateTime sentAt;

    public BroadcastLog() {
    }

    public BroadcastLog(Long id, EventUpdate eventUpdate, User subscriber, String deliveryStatus) {
        this.id = id;
        this.eventUpdate = eventUpdate;
        this.subscriber = subscriber;
        this.deliveryStatus = deliveryStatus;
    }

    @PrePersist
    public void onCreate() {
        this.sentAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
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

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
 
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
