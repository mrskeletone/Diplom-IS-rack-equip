package com.example.mes_opcua.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.example.RfidScanEvent;

@Component
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, RfidScanEvent> kafkaTemplate;

    public void sendMessage(RfidScanEvent message, String topic){
        kafkaTemplate.send(topic,message);
    }
}
