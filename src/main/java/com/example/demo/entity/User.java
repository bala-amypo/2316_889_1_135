package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users") // Table name "users" [cite: 38]
@Data
@NoArgsConstructor // No-args constructor [cite: 31]
@AllArgsConstructor // All fields constructor [cite: 31]
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long, PK, auto-generated [cite: 25]

    @Column(nullable = false)
    private String fullName; // Required [cite: 26]

    @Column(nullable = false, unique = true)
    private String email; // Required, unique [cite: 27]

    @Column(nullable = false)
    private String password; // Required [cite: 28]

    @Column(nullable = false)
    private String role = "SUBSCRIBER"; // Defaults to SUBSCRIBER [cite: 37]

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // Auto-populated [cite: 36]
    }
}