package com.example.project.controller;

import com.example.project.entity.Subscription;
import com.example.project.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{eventId}")
    public Subscription subscribe(@RequestParam Long userId,
                                  @PathVariable Long eventId) {
        return subscriptionService.subscribe(userId, eventId);
    }

    @DeleteMapping("/{eventId}")
    public void unsubscribe(@RequestParam Long userId,
                            @PathVariable Long eventId) {
        subscriptionService.unsubscribe(userId, eventId);
    }

    @GetMapping("/user/{userId}")
    public List<Subscription> getForUser(@PathVariable Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    @GetMapping("/check/{userId}/{eventId}")
    public boolean check(@PathVariable Long userId,
                         @PathVariable Long eventId) {
        return subscriptionService.isSubscribed(userId, eventId);
    }
}
