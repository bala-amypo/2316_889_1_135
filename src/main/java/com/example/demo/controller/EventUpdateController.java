package com.example.project.controller;

import com.example.project.entity.EventUpdate;
import com.example.project.service.EventUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/updates")
public class EventUpdateController {

    private final EventUpdateService updateService;

    public EventUpdateController(EventUpdateService updateService) {
        this.updateService = updateService;
    }

    @PostMapping
    public EventUpdate publish(@RequestBody EventUpdate update) {
        return updateService.publishUpdate(update);
    }

    @GetMapping("/event/{eventId}")
    public List<EventUpdate> getForEvent(@PathVariable Long eventId) {
        return updateService.getUpdatesForEvent(eventId);
    }

    @GetMapping("/{id}")
    public EventUpdate get(@PathVariable Long id) {
        return updateService.getUpdateById(id);
    }
}
