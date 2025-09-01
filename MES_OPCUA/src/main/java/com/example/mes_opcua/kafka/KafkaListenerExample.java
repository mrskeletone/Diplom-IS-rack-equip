package com.example.mes_opcua.kafka;


import com.example.mes_opcua.MesOpcUaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.example.RfidScanEvent;

@Component

public class KafkaListenerExample {
    private MesOpcUaService mesOpcUaService;
    private KafkaSender kafkaSender;
    @Autowired
    public KafkaListenerExample(MesOpcUaService mesOpcUaService,KafkaSender kafkaSender) {
        this.mesOpcUaService = mesOpcUaService;
        this.kafkaSender=kafkaSender;
    }

    @KafkaListener(topics = "EPC_IDReadersRFID2",
            groupId = "delivery-group", containerFactory = "RfidScanRequestKafkaListenerContainerFactory")
    void listenerTemperature(RfidScanEvent data) {
        data.setLocation(mesOpcUaService.getNewLocation(data.getEpc()));
        System.out.println(data.getEpc()+" "+data.getLocation());
        System.out.println("Пришел на test");
        kafkaSender.sendMessage(data,"location");
    }


}
