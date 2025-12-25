package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository eventUpdateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;

    public BroadcastServiceImpl(EventUpdateRepository eventUpdateRepository,
                                SubscriptionRepository subscriptionRepository,
                                BroadcastLogRepository broadcastLogRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }

    @Override
    public void broadcastUpdate(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId).orElseThrow();
        BroadcastLog log = new BroadcastLog();
        log.setEventUpdate(update);
        log.setDeliveryStatus(DeliveryStatus.SENT);
        broadcastLogRepository.save(log);
    }

    @Override
    public void recordDelivery(Long updateId, Long userId, boolean success) {
        EventUpdate update = eventUpdateRepository.findById(updateId).orElseThrow();
        BroadcastLog log = new BroadcastLog();
        log.setEventUpdate(update);
        log.setDeliveryStatus(success ? DeliveryStatus.SENT : DeliveryStatus.FAILED);
        broadcastLogRepository.save(log);
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
}
