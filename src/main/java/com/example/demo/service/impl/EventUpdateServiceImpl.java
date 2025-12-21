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

    public EventUpdateServiceImpl(EventUpdateRepository eur, EventRepository er, BroadcastService bs) {
        this.eventUpdateRepository = eur;
        this.eventRepository = er;
        this.broadcastService = bs;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        EventUpdate saved = eventUpdateRepository.save(update);
        broadcastService.broadcastUpdate(saved.getId());
        return saved;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventId(eventId);
    }

    @Override
    public Optional<EventUpdate> getUpdateById(Long id) {
        return eventUpdateRepository.findById(id);
    }

    @Override
    public List<EventUpdate> getAllUpdates() {
        return eventUpdateRepository.findAll();
    }
}