package com.example.demo.service;

import com.example.demo.entity.EventUpdate;
import java.util.List;
import java.util.Optional;

public interface EventUpdateService {
    EventUpdate publishUpdate(EventUpdate update); [cite: 208]
    List<EventUpdate> getUpdatesForEvent(Long eventId); [cite: 209]
    Optional<EventUpdate> getUpdateById(Long id); [cite: 210]
    List<EventUpdate> getAllUpdates(); [cite: 211]
}