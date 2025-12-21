package com.example.project.controller;

import com.example.project.entity.Event;
import com.example.project.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event create(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return eventService.getById(id);
    }

    public List<Event> getActive() {
        return eventService.getActiveEvents();
    }

    @PatchMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        eventService.deactivateEvent(id);
    }
}
