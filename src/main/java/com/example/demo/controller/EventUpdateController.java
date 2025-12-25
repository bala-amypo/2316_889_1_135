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

    @PostMapping
    public EventUpdate publish(@RequestBody EventUpdateRequest request) {
        EventUpdate update = new EventUpdate();
        Event event = new Event();
        event.setId(request.eventId);

        update.setEvent(event);
        update.setUpdateContent(request.updateContent);
        update.setUpdateType(request.updateType);

        return eventUpdateService.publishUpdate(update);
    }

    @GetMapping("/event/{eventId}")
    public List<EventUpdate> getByEvent(@PathVariable Long eventId) {
        return eventUpdateService.getUpdatesForEvent(eventId);
    }

    @GetMapping("/{id}")
    public EventUpdate getById(@PathVariable Long id) {
        return eventUpdateService.getUpdateById(id);
    }
}
