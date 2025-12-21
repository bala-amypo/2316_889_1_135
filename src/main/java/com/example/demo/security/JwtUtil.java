package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    private String secret = "EventBroadcastingSecretKey12345678901234567890"; // Example secret
    private long validityInMs = 3600000; // 1 hour [cite: 267]

    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId) // [cite: 269]
                .claim("role", role) // [cite: 269]
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMs))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true; // [cite: 270]
        } catch (Exception e) {
            return false; // [cite: 270]
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject(); // [cite: 271]
    }
}