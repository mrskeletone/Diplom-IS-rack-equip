package com.example.rfid_mbtcp.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {
    @Bean
    public NewTopic EPC_IDReadersRFID(){
        return TopicBuilder.name("EPC_IDReadersRFID2").build();
    }
    @Bean
    public  NewTopic Location(){return TopicBuilder.name("location").build();}

}
