package com.example.project.service;

import com.example.project.entity.EventUpdate;
import java.util.List;

public interface EventUpdateService {

    EventUpdate publishUpdate(EventUpdate update);

    List<EventUpdate> getUpdatesForEvent(Long eventId);

    EventUpdate getUpdateById(Long id);

    EventUpdate createUpdate(Long eventId, EventUpdate update);
}
