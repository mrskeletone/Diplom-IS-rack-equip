package com.example.mobileapp;

import com.example.mobileapp.DTO.DeliveryLocationResponse;
import com.example.mobileapp.DTO.RfidScanRequest;
import com.example.mobileapp.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.RfidScanEvent;

@RestController
@RequestMapping("/api/rfid")
public class RfidController {
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private DeliveryUpdateCache updateCache;
    @PostMapping("/scans")
    public ResponseEntity<Void> handleScan(@RequestBody RfidScanRequest request) {
        kafkaSender.sendMessage(new RfidScanEvent(request.getEpc(), request.getReaderId(),
                "", request.getUsername()),"EPC_IDReadersRFID2");
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/updates")
    public ResponseEntity<DeliveryLocationResponse> checkUpdate(
            @RequestParam String epc,
            @RequestParam(defaultValue = "30") int timeoutSec) throws InterruptedException {
        long endTime = System.currentTimeMillis() + timeoutSec * 1000L;
        while (System.currentTimeMillis() < endTime) {
            DeliveryLocationResponse update = updateCache.getUpdate(epc);
            if (update != null) {
                return ResponseEntity.ok(update);
            }
            Thread.sleep(1000);
        }
        return ResponseEntity.noContent().build();
    }
}
