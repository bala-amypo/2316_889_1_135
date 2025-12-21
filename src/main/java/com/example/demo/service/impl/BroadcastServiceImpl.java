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

    public BroadcastServiceImpl(BroadcastLogRepository blr, SubscriptionRepository sr, EventUpdateRepository eur) {
        this.broadcastLogRepository = blr;
        this.subscriptionRepository = sr;
        this.eventUpdateRepository = eur;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId).orElseThrow();
        List<Subscription> subs = subscriptionRepository.findByEventId(update.getEvent().getId());
        
        for (Subscription sub : subs) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus("SENT");
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }

    @Override
    public void recordDelivery(Long updateId, Long subscriberId, boolean successful) {
        // Logic to update status to SENT or FAILED
    }

    @Override
    public List<BroadcastLog> getAllLogs() {
        return broadcastLogRepository.findAll();
    }
}