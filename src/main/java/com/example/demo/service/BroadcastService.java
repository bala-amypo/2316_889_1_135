package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.BroadcastLog;

public interface BroadcastService {

    void broadcastUpdate(Long eventUpdateId);

    void recordDelivery(Long updateId, Long subscriberId, boolean success);

    List<BroadcastLog> getLogsForUpdate(Long updateId);
}
