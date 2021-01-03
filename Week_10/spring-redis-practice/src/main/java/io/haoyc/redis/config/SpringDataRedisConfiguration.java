package io.haoyc.redis.config;

import io.haoyc.redis.ps.pub.RedisMessagePublisher;
import io.haoyc.redis.ps.sub.RedisMessageSubsriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class SpringDataRedisConfiguration {
    @Autowired
    JedisConnectionFactory jedisConnectionFactory;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new RedisMessageSubsriber());
    }

    @Bean
    public RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    @Bean
    public RedisMessagePublisher redisPublisher() {
        return new RedisMessagePublisher(stringRedisTemplate, topic());
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("mq");
    }
}
