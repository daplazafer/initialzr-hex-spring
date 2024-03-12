package com.ggroupid.aartifactid.redis.service;

import com.ggroupid.aartifactid.domain.model.Traceable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Traceable
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void store(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Optional<Object> get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }
}
