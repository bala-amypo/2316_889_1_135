package com.example.project.service;

import com.example.project.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    Subscription subscribe(Long userId, Long eventId);

    void unsubscribe(Long userId, Long eventId);

    List<Subscription> getSubscriptionsForUser(Long userId);

    List<Subscription> getUserSubscriptions(Long userId);

    boolean isSubscribed(Long userId, Long eventId);
}
