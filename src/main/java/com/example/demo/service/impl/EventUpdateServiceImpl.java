package com.example.project.service.impl;

import com.example.project.entity.Event;
import com.example.project.entity.EventUpdate;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.EventRepository;
import com.example.project.repository.EventUpdateRepository;
import com.example.project.service.EventUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;

    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository,
                                  EventRepository eventRepository) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public EventUpdate createUpdate(Long eventId, EventUpdate update) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        update.setEvent(event);
        return eventUpdateRepository.save(update);
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventIdOrderByTimestampAsc(eventId);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event update not found"));
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        return eventUpdateRepository.save(update);
    }
}
