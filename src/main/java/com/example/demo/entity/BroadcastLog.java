package com.example.project.entity;

import jakarta.persistence.*;

@Entity
public class BroadcastLog {
    @Id
    private Long id;
    @ManyToOne
    private EventUpdate eventUpdate;
    @ManyToOne
    private User subscriber;
    private DeliveryStatus deliveryStatus = DeliveryStatus.SENT;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) { this.eventUpdate = eventUpdate; }

    public User getSubscriber() { return subscriber; }
    public void setSubscriber(User subscriber) { this.subscriber = subscriber; }

    public DeliveryStatus getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public BroadcastLog(EventUpdate eventUpdate, User subscriber, DeliveryStatus deliveryStatus) {
        this.eventUpdate = eventUpdate;
        this.subscriber = subscriber;
        this.deliveryStatus = deliveryStatus;
    }
    public BroadcastLog() {
    }
    
}
