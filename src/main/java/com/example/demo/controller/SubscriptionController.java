package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final JwtUtil jwtUtil;

    public SubscriptionController(SubscriptionService subscriptionService, JwtUtil jwtUtil) {
        this.subscriptionService = subscriptionService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<Subscription> subscribe(@RequestHeader("Authorization") String token, @PathVariable Long eventId) {
        Long userId = jwtUtil.getUserIdFromToken(token.substring(7));
        return ResponseEntity.status(201).body(subscriptionService.subscribe(userId, eventId));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> unsubscribe(@RequestHeader("Authorization") String token, @PathVariable Long eventId) {
        Long userId = jwtUtil.getUserIdFromToken(token.substring(7));
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @GetMapping("/check/{userId}/{eventId}")
    public ResponseEntity<Boolean> checkSubscription(@PathVariable Long userId, @PathVariable Long eventId) {
        return ResponseEntity.ok(subscriptionService.isSubscribed(userId, eventId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }
}