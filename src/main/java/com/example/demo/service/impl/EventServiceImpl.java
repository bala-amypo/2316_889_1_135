package com.example.project.service.impl;

import com.example.project.entity.Event;
import com.example.project.entity.Role;
import com.example.project.entity.User;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.EventRepository;
import com.example.project.repository.UserRepository;
import com.example.project.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository,
                            UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event createEvent(Event event) {
        if (event.getPublisher() == null || event.getPublisher().getId() == null) {
            throw new IllegalArgumentException("Only PUBLISHER or ADMIN can create events");
        }

        User publisher = userRepository.findById(event.getPublisher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!(publisher.getRole() == Role.PUBLISHER || publisher.getRole() == Role.ADMIN)) {
            throw new IllegalArgumentException("Only PUBLISHER or ADMIN can create events");
        }

        event.setPublisher(publisher);
        event.setActive(true);
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event updated) {
        Event existing = getById(id);

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setLocation(updated.getLocation());
        existing.setCategory(updated.getCategory());

        return eventRepository.save(existing);
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
        event.setActive(false);
        eventRepository.save(event);
    }
}
