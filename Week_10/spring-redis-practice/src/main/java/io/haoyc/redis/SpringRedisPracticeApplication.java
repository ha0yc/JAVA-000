package io.haoyc.redis;

//import io.haoyc.redis.lock.RedisLock;
import io.haoyc.redis.counter.RedisCounter;
import io.haoyc.redis.lock.RedisLock;
import io.haoyc.redis.ps.pub.RedisMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

@SpringBootApplication
public class SpringRedisPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisPracticeApplication.class, args);
	}

	@Autowired
	StringRedisTemplate redisTemplate;

	@Autowired
	RedisLock redisLock;

	@Autowired
	RedisCounter redisCounter;

	@Autowired
	JedisPool jedisPool;

	@Bean
	public ApplicationRunner lockRunner() {
		return args -> {
			String timeStamp = String.valueOf(System.currentTimeMillis());
			String requestId1 = UUID.randomUUID().toString().replace("-", "");
			if (!redisLock.lock(timeStamp, requestId1, 5000)) {
				System.out.println("第一次无法取到锁: " + timeStamp);
			} else {
				System.out.println("第一次成功取到锁: " + timeStamp);
			}

			String requestId2 = UUID.randomUUID().toString().replace("-", "");

			if (!redisLock.lock(timeStamp, requestId2, 5000)) {
				System.out.println("第二次无法取到锁: " + timeStamp);
			} else {
				System.out.println("第二次成功取到锁: " + timeStamp);
			}

			redisLock.release(timeStamp, requestId1);

		};
	}

	@Bean
	public ApplicationRunner counterRunner() {
		return args -> {
			String uuid = UUID.randomUUID().toString().replace("-","");
			redisCounter.count(uuid);
			System.out.println(jedisPool.getResource().get(uuid));
			jedisPool.getResource().del(uuid);
		};
	}

	@Autowired
	RedisMessagePublisher publisher;

	@Bean
	public ApplicationRunner pubsubRunner() {
		return args -> {
			publisher.publish("query order");
			System.out.println("message send: " + "query order");
		};
	}

}
