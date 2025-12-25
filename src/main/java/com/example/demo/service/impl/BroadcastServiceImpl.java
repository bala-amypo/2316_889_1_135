package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.User;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final BroadcastLogRepository broadcastLogRepository;
    private final EventUpdateRepository eventUpdateRepository;
    private final UserRepository userRepository;

    public BroadcastServiceImpl(BroadcastLogRepository broadcastLogRepository,
                                EventUpdateRepository eventUpdateRepository,
                                UserRepository userRepository) {
        this.broadcastLogRepository = broadcastLogRepository;
        this.eventUpdateRepository = eventUpdateRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void triggerBroadcast(Long updateId) {
        EventUpdate update = eventUpdateRepository.findById(updateId).orElseThrow();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            BroadcastLog log = new BroadcastLog();
            log.setId(0L);
            log.setDeliveryStatus("SENT");
            broadcastLogRepository.save(log);
        }
    }

    @Override
    public List<BroadcastLog> getLogsForUpdate(Long updateId) {
        return broadcastLogRepository.findByEventUpdateId(updateId);
    }
}
