package com.example.demo.controller;

import com.example.demo.service.SubscriptionService;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final JwtUtil jwtUtil;

    public SubscriptionController(SubscriptionService subscriptionService, JwtUtil jwtUtil) {
        this.subscriptionService = subscriptionService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/{eventId}") [cite: 255]
    public ResponseEntity<?> subscribe(@RequestHeader("Authorization") String token, @PathVariable Long eventId) {
        Long userId = jwtUtil.getUserIdFromToken(token.substring(7));
        return ResponseEntity.ok(subscriptionService.subscribe(userId, eventId));
    }

    @DeleteMapping("/{eventId}") [cite: 256]
    public ResponseEntity<?> unsubscribe(@RequestHeader("Authorization") String token, @PathVariable Long eventId) {
        Long userId = jwtUtil.getUserIdFromToken(token.substring(7));
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.noContent().build();
    }
}