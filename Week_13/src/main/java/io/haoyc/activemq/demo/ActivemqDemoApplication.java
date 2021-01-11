package io.haoyc.activemq.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Queue;

@SpringBootApplication
public class ActivemqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqDemoApplication.class, args);
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("test.haoyc");
	}

	@Autowired
	TestProducer producer;

	@Bean
	public ApplicationRunner runner() {
		return args -> {
			for (int i = 0; i < 100; i++) {
				producer.sendMsg("message_" + i);
			}
		};
	}
}
