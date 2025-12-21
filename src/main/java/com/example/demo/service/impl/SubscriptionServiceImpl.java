package com.example.demo.service.impl;

import com.example.demo.entity.Subscription;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.*;
import com.example.demo.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subRepo, UserRepository userRepo, EventRepository eventRepo) {
        this.subscriptionRepository = subRepo; // [cite: 213]
        this.userRepository = userRepo;
        this.eventRepository = eventRepo;
    }

    @Override
    public Subscription subscribe(Long userId, Long eventId) {
        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new BadRequestException("Already subscribed"); // [cite: 215]
        }
        // Logic to fetch entities and save...
        return new Subscription(); 
    }
}