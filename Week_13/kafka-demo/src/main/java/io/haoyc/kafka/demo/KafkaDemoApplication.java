package io.haoyc.kafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

	@Autowired
	Producer producer;

	@Bean
	public ApplicationRunner kafkaRunner() {
		return args -> {
			for (int i = 0; i < 100; i++) {
				producer.send("hello-" + i);
				System.out.println("send message: hello-" + i);
			}
		};
	}

}
