package io.haoyc.activemq.demo;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;

@Component
public class TestProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    public void sendMsg(String msg) {
        System.out.println("发送消息内容 :" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
    }
}
