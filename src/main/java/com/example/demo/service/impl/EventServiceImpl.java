package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) { // Constructor injection [cite: 197]
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        // Validation for role is typically handled by Security or Controller logic
        return eventRepository.save(event); // [cite: 199]
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found")); // [cite: 201]
    }

    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue(); // [cite: 202]
    }
}