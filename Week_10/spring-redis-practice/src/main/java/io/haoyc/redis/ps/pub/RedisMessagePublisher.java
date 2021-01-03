package io.haoyc.redis.ps.pub;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MessagePublisher {
    private StringRedisTemplate redisTemplate;
    private ChannelTopic channelTopic;

    @Override
    public void publish(String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }

    public RedisMessagePublisher(StringRedisTemplate redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = topic;
    }
}
