package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // POST /{eventId} – Subscribe
    @PostMapping("/{eventId}")
    public Subscription subscribe(@PathVariable Long eventId,
                                  @RequestParam Long userId) {
        return subscriptionService.subscribe(userId, eventId);
    }

    // DELETE /{eventId} – Unsubscribe
    @DeleteMapping("/{eventId}")
    public void unsubscribe(@PathVariable Long eventId,
                            @RequestParam Long userId) {
        subscriptionService.unsubscribe(userId, eventId);
    }

    // GET /user/{userId} – List subscriptions
    @GetMapping("/user/{userId}")
    public List<Subscription> userSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

    // GET /check/{userId}/{eventId} – Check status
    @GetMapping("/check/{userId}/{eventId}")
    public boolean check(@PathVariable Long userId,
                         @PathVariable Long eventId) {
        return subscriptionService.isSubscribed(userId, eventId);
    }
}
