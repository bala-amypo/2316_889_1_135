package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        String role = event.getPublisher().getRole();
        if (!"ADMIN".equals(role) && !"PUBLISHER".equals(role)) {
            throw new BadRequestException("Only PUBLISHER or ADMIN can create events");
        }
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event updated) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found");
        }
        updated.setId(id);
        return eventRepository.save(updated);
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    @Override
    public void deactivateEvent(Long id) {
        Event event = getById(id);
        event.setIsActive(false);
        eventRepository.save(event);
    }

    @Override
    public Event getEventById(Long id) { return getById(id); }
}