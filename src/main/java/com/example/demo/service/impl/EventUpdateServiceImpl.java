package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventUpdateService;
import com.example.demo.service.BroadcastService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {
    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private final BroadcastService broadcastService;

    // Strict constructor order: EventUpdateRepository, EventRepository, BroadcastService [cite: 206]
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository, 
                                  BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        EventUpdate savedUpdate = eventUpdateRepository.save(update); [cite: 208]
        broadcastService.broadcastUpdate(savedUpdate.getId()); [cite: 208]
        return savedUpdate;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId); [cite: 209]
    }

    @Override
    public Optional<EventUpdate> getUpdateById(Long id) {
        return eventUpdateRepository.findById(id); [cite: 210]
    }

    @Override
    public List<EventUpdate> getAllUpdates() {
        return eventUpdateRepository.findAll(); [cite: 211]
    }
}