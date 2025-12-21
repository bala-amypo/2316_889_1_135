package com.example.demo.service.impl;
// ... (existing imports)

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    // ... (existing constructor and methods)

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public void unsubscribe(Long userId, Long eventId) {
        subscriptionRepository.findByUserIdAndEventId(userId, eventId)
            .ifPresent(subscriptionRepository::delete);
    }

    @Override
    public List<Subscription> getSubscriptionsByUser(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public boolean isSubscribed(Long userId, Long eventId) {
        return subscriptionRepository.existsByUserIdAndEventId(userId, eventId);
    }
}