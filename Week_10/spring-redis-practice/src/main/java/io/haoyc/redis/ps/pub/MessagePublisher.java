package io.haoyc.redis.ps.pub;

public interface MessagePublisher {
    void publish(String message);
}
