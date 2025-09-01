package com.example.mobileapp.kafka;


import com.example.mobileapp.DeliveryUpdateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.example.RfidScanEvent;

@Component

public class KafkaListenerExample {

    @Autowired
    private DeliveryUpdateCache updateCache;


    @KafkaListener(topics = "location",
            groupId = "delivery-group", containerFactory = "RfidScanRequestKafkaListenerContainerFactory")
    void listenerLocation(RfidScanEvent data) {
        System.out.println("Сработало пересылка:"+data.getLocation());
          updateCache.addUpdate(data.getEpc(), data.getLocation());
    }


}
//    @KafkaListener(topics = "test2",
//            groupId = "delivery-group", containerFactory = "RfidScanRequestKafkaListenerContainerFactory")
//    void listenerTest(RfidScanEvent data) {
//        System.out.println("Сработало пересылка:"+data.getLocation());
//        //  updateCache.addUpdate(data.getEpc(), data.getLocation());
//    }