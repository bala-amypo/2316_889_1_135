package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; [cite: 41]

    @Column(nullable = false)
    private String title; [cite: 42]

    @Column(nullable = false)
    private String description; [cite: 43]

    @Column(nullable = false)
    private String location; [cite: 44]

    private String category; [cite: 45]

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    private User publisher; [cite: 46]

    private Boolean isActive = true; [cite: 47, 53]

    private LocalDateTime createdAt; [cite: 48]
    private LocalDateTime lastUpdatedAt; [cite: 49]

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); [cite: 55]
        this.lastUpdatedAt = LocalDateTime.now(); [cite: 56]
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedAt = LocalDateTime.now(); [cite: 56]
    }
}