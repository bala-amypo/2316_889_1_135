package com.example.demo.controller;

import com.example.demo.dto.EventUpdateRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {

    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    // POST / – Publish update
    @PostMapping
    public EventUpdate publish(@RequestBody EventUpdateRequest request) {
        Event event = new Event();
        event.setId(request.eventId);

        EventUpdate update = new EventUpdate();
        update.setEvent(event);
        update.setUpdateContent(request.updateContent);
        update.setUpdateType(request.updateType);

        return eventUpdateService.publishUpdate(update);
    }

    // GET /event/{eventId} – Get updates for event
    @GetMapping("/event/{eventId}")
    public List<EventUpdate> getForEvent(@PathVariable Long eventId) {
        return eventUpdateService.getUpdatesForEvent(eventId);
    }

    // GET /{id} – Get update by ID
    @GetMapping("/{id}")
    public EventUpdate getById(@PathVariable Long id) {
        return eventUpdateService.getUpdateById(id);
    }
}
