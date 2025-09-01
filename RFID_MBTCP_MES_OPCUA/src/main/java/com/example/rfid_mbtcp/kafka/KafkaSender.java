package com.example.rfid_mbtcp.kafka;

import org.example.RfidScanEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, RfidScanEvent> kafkaTemplate;

    public void sendMessage(RfidScanEvent message, String topic){
        kafkaTemplate.send(topic,message);
    }
}
