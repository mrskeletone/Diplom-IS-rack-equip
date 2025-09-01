package com.example.mobileapp;

import com.example.mobileapp.DTO.DeliveryLocationResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeliveryUpdateCache {
    private final Map<String, DeliveryLocationResponse> updates = new ConcurrentHashMap<>();
    public void addUpdate(String epc, String location) {
        updates.put(epc, new DeliveryLocationResponse(epc, location));
    }
    public DeliveryLocationResponse getUpdate(String epc) {
        return updates.remove(epc);
    }
}
