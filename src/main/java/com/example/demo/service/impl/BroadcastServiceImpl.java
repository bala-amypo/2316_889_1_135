package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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
    public void broadcastUpdate(Long eventUpdateId) {
        EventUpdate update = eventUpdateRepository.findById(eventUpdateId).orElseThrow();

        List<User> subscribers =
                subscriptionRepository.findSubscribersByEventId(
                        update.getEvent().getId()
                );

        for (User user : subscribers) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(user);
            log.setDeliveryStatus(DeliveryStatus.SENT);

            broadcastLogRepository.save(log);
        }
    }

    @Override
    public void recordDelivery(Long updateId, Long subscriberId, boolean success) {
        List<BroadcastLog> logs =
                broadcastLogRepository.findByEventUpdateId(updateId);

        for (BroadcastLog log : logs) {
            if (log.getSubscriber().getId().equals(subscriberId)) {
                log.setDeliveryStatus(
                        success ? DeliveryStatus.SENT : DeliveryStatus.FAILED
                );
                broadcastLogRepository.save(log);
                return;
            }
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
}
