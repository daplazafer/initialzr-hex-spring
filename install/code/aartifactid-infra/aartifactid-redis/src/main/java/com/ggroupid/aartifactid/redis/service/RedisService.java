package com.ggroupid.aartifactid.redis.service;

import com.ggroupid.aartifactid.domain.model.Traceable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Traceable
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void store(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
