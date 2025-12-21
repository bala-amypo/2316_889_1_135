package com.example.project.service.impl;

import com.example.project.entity.Event;
import com.example.project.entity.Subscription;
import com.example.project.entity.User;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.EventRepository;
import com.example.project.repository.SubscriptionRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   UserRepository userRepository,
                                   EventRepository eventRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public Subscription subscribe(Long userId, Long eventId) {

        if (subscriptionRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalArgumentException("Already subscribed");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setEvent(event);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public void unsubscribe(Long userId, Long eventId) {
        Subscription subscription = subscriptionRepository
                .findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));

        subscriptionRepository.delete(subscription);
    }

    @Override
    public List<Subscription> getSubscriptionsForUser(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public boolean isSubscribed(Long userId, Long eventId) {
        return subscriptionRepository.existsByUserIdAndEventId(userId, eventId);
    }
}
