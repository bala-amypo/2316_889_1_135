package com.example.demo.service.impl;

import com.example.demo.entity.Subscription;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SubscriptionServiceImpl(SubscriptionRepository sr, UserRepository ur, EventRepository er) {
        this.subscriptionRepository = sr;
        this.userRepository = ur;
        this.eventRepository = er;
    }

    @Override
    public Subscription subscribe(Long userId, Long eventId) {
        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new BadRequestException("Already subscribed");
        }
        Subscription sub = new Subscription();
        sub.setUser(userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found")));
        sub.setEvent(eventRepository.findById(eventId).orElseThrow(() -> new ResourceNotFoundException("Event not found")));
        return subscriptionRepository.save(sub);
    }
}