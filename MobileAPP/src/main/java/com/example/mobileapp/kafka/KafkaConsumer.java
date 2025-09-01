package com.example.mobileapp.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;
import org.example.RfidScanEvent;

@Configuration
@EnableKafka
public class KafkaConsumer {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    public ConsumerFactory<String, RfidScanEvent> RfidScanRequestConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        JsonDeserializer<RfidScanEvent> deserializer = new JsonDeserializer<>(RfidScanEvent.class);
        deserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RfidScanEvent> RfidScanRequestKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RfidScanEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(RfidScanRequestConsumerFactory());
        factory.setCommonErrorHandler(new DefaultErrorHandler(
                (record, exception) -> {
                    System.err.println("Ошибка обработки сообщения: " + record);
                    exception.printStackTrace();
                },
                new FixedBackOff(1000L, 2L)
        ));
        return factory;
    }
}
