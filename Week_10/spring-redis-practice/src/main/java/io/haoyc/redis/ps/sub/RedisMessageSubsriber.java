package io.haoyc.redis.ps.sub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageSubsriber implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("message received: " + message.toString());
    }
}
