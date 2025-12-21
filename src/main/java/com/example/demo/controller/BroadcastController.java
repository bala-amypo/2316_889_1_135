package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
@Tag(name = "Broadcast Controller")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping("/trigger/{updateId}")
    public void trigger(@PathVariable Long updateId) {
        broadcastService.triggerBroadcast(updateId);
    }

    @GetMapping("/logs/{updateId}")
    public List<BroadcastLog> getLogs(@PathVariable Long updateId) {
        return broadcastService.getLogsForUpdate(updateId);
    }
}
