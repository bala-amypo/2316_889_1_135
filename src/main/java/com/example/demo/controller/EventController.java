package com.example.demo.controller;

import com.example.demo.dto.EventRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // POST / – Create event
    @PostMapping
    public Event create(@RequestBody EventRequest request) {
        Event event = new Event();
        User publisher = new User();
        publisher.setId(request.publisherId);

        event.setTitle(request.title);
        event.setDescription(request.description);
        event.setLocation(request.location);
        event.setCategory(request.category);
        event.setPublisher(publisher);

        return eventService.createEvent(event);
    }

    // PUT /{id} – Update event
    @PutMapping("/{id}")
    public Event update(@PathVariable Long id,
                        @RequestBody EventRequest request) {

        Event event = new Event();
        event.setTitle(request.title);
        event.setDescription(request.description);
        event.setLocation(request.location);
        event.setCategory(request.category);

        return eventService.updateEvent(id, event);
    }

    // GET /{id} – Get event
    @GetMapping("/{id}")
    public Event get(@PathVariable Long id) {
        return eventService.getById(id);
    }

    // GET /active – List active events
    @GetMapping("/active")
    public List<Event> activeEvents() {
        return eventService.getActiveEvents();
    }

    // PATCH /{id}/deactivate – Deactivate
    @PatchMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        eventService.deactivateEvent(id);
    }
}
