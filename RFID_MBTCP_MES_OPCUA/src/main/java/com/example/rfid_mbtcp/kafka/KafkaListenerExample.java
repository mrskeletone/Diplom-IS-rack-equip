package com.example.rfid_mbtcp.kafka;


import com.example.rfid_mbtcp.Entity.Logs_taking;
import com.example.rfid_mbtcp.Entity.RFID_readers;
import com.example.rfid_mbtcp.mes.MesOpcUaService;
import com.example.rfid_mbtcp.service.ServLogs_taking;
import com.example.rfid_mbtcp.service.ServRFIDReaders;
import com.example.rfid_mbtcp.util.Type;
import com.example.rfid_mbtcp.util.Util;
import org.example.RfidScanEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaListenerExample {
    private MesOpcUaService mesOpcUaService;
    private KafkaSender kafkaSender;
    private final ServRFIDReaders servRFIDReaders;
    private final ServLogs_taking servLogsTaking;
    @Autowired
    public KafkaListenerExample(MesOpcUaService mesOpcUaService, KafkaSender kafkaSender, ServRFIDReaders servRFIDReaders, ServLogs_taking servLogsTaking) {
        this.mesOpcUaService = mesOpcUaService;
        this.kafkaSender=kafkaSender;
        this.servRFIDReaders = servRFIDReaders;
        this.servLogsTaking = servLogsTaking;
    }

    @KafkaListener(topics = "EPC_IDReadersRFID2",
            groupId = "delivery-group", containerFactory = "RfidScanRequestKafkaListenerContainerFactory")
    void listener(RfidScanEvent data) {
        data.setLocation(mesOpcUaService.getNewLocation(data.getEpc()));
        Logs_taking logsTaking=new Logs_taking();
        logsTaking.setEmployees(data.getUsername());
        logsTaking.setLocation(data.getLocation());
        RFID_readers readers=servRFIDReaders.findById(Util.getIDOnIdMachine(Integer.parseInt(data.getReaderId()), Type.TAKING));
        logsTaking.setEpc(data.getEpc());
        logsTaking.setTime(LocalDateTime.now());
        logsTaking.setIdRFID_readers(readers);
        servLogsTaking.save(logsTaking);
        kafkaSender.sendMessage(data,"location");
    }


}
