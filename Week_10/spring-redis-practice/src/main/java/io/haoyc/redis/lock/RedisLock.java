package io.haoyc.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class RedisLock {
    @Autowired
    private JedisPool jedisPool;
    /**
     * 加锁
     * @param lockKey 加锁的Key
     * @param timeStamp 时间戳：当前时间+超时时间
     * @return
     */
    public synchronized boolean lock(String lockKey,String requestId, long timeout){
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(lockKey, requestId, SetParams.setParams().nx().px(timeout));

        if ("OK".equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 释放锁
     * @param lockKey
     * @param timeStamp
     */
    public synchronized boolean release(String lockKey,String requestId){
        Jedis jedis = jedisPool.getResource();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";


        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (1L == (long) result) {
            return true;
        }

        return false;


    }
}
