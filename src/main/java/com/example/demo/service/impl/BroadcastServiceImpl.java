package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastLogRepository broadcastLogRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final EventUpdateRepository eventUpdateRepository;

    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository,
                                SubscriptionRepository subscriptionRepository,
                                EventUpdateRepository eventUpdateRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.eventUpdateRepository = eventUpdateRepository;
    }

    @Override
    public void triggerBroadcast(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        List<Subscription> subs =
                subscriptionRepository.findByUserId(update.getEvent().getId());

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
}
