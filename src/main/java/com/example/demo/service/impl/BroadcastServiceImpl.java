package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {
    private final BroadcastLogRepository broadcastLogRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventUpdateRepository eventUpdateRepository;

    // Constructor order: BroadcastLogRepository, SubscriptionRepository, EventUpdateRepository [cite: 221]
    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository, 
                                SubscriptionRepository subscriptionRepository, 
                                EventUpdateRepository eventUpdateRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventUpdateRepository = eventUpdateRepository;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new RuntimeException("Update not found")); // [cite: 224]

        List<Subscription> subscriptions = subscriptionRepository.findByEventId(update.getEvent().getId()); // [cite: 226]

        for (Subscription sub : subscriptions) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus("SENT"); // Status defaults to SENT [cite: 96, 227]
            broadcastLogRepository.save(log);
        }
    }
}