package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    // POST /trigger/{updateId} – Manual broadcast trigger
    @PostMapping("/trigger/{updateId}")
    public void trigger(@PathVariable Long updateId) {
        broadcastService.broadcastUpdate(updateId);
    }

    // GET /logs/{updateId} – Get logs
    @GetMapping("/logs/{updateId}")
    public List<BroadcastLog> logs(@PathVariable Long updateId) {
        return broadcastService.getLogsForUpdate(updateId);
    }
}
