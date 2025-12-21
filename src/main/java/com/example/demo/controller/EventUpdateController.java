package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/updates")
@Tag(name = "Event Update Controller")
public class EventUpdateController {

    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    @PostMapping
    public EventUpdate publish(@RequestBody EventUpdate update) {
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
