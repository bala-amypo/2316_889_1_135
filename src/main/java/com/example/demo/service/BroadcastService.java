package com.example.project.service;

import com.example.project.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {

    void broadcastUpdate(Long updateId);

    List<BroadcastLog> getLogsForUpdate(Long updateId);

    void recordDelivery(Long updateId, Long subscriberId, boolean failed);
}
