package io.haoyc.activemq.demo;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @JmsListener(destination = "test.haoyc")
    public void receiveMsg(String text) {
        System.out.println("接收到消息 : "+text);
    }
}
