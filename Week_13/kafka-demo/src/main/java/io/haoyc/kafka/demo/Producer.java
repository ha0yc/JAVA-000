package io.haoyc.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void send(String message) {
        kafkaTemplate.send("test", message);
    }
}
