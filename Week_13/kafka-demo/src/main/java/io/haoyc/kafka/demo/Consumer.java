package io.haoyc.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "test")
    public void listener(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println("message received: " + value);
    }
}
