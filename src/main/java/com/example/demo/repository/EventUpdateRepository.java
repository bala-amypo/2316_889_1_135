package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {

    List<EventUpdate> findByEventId(Long eventId);

    List<EventUpdate> findByEventIdOrderByPostedAtAsc(Long eventId);

    Optional<EventUpdate> findById(Long id);
}
