package io.haoyc.redis.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisCounter {

    @Autowired
    public JedisPool jedisPool;

    public void count(String key) {
        Jedis jedis = jedisPool.getResource();
        if (jedis.setnx(key, "1") <= 0) {
            jedis.incr(key);
        }
    }
}
