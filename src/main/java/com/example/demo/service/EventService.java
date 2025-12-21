package com.example.project.service;

import com.example.project.entity.Event;

import java.util.List;

public interface EventService {

    Event createEvent(Event event);

    Event updateEvent(Long id, Event updated);

    Event getById(Long id);

    List<Event> getActiveEvents();

    void deactivateEvent(Long id);
}
