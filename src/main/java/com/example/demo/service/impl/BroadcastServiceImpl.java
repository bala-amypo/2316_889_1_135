package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastLogRepository broadcastLogRepository;
    private final EventUpdateRepository eventUpdateRepository;

    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository,
                                EventUpdateRepository eventUpdateRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.eventUpdateRepository = eventUpdateRepository;
    }

    @Override
    public void recordDelivery(Long updateId, Long userId, boolean success) {
        EventUpdate update = eventUpdateRepository.findById(updateId).orElseThrow();
        BroadcastLog log = new BroadcastLog();
        log.setEventUpdate(update);
        log.setDeliveryStatus(success ? "SENT" : "FAILED");
        broadcastLogRepository.save(log);
    }
}
