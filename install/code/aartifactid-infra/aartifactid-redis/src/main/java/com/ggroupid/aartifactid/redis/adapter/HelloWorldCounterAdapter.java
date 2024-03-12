package com.ggroupid.aartifactid.redis.adapter;

import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import com.ggroupid.aartifactid.redis.dto.HelloWorldCounterDto;
import com.ggroupid.aartifactid.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HelloWorldCounterAdapter implements HelloWorldCounterPort {

    private static final String HELLO_WORLD_COUNTER_KEY = "hello-world";

    private final RedisService redisService;

    @Override
    public void increment() {

        var counter = getCounter();

        redisService.store(HELLO_WORLD_COUNTER_KEY, new HelloWorldCounterDto(counter + 1));
    }

    @Override
    public int getCounter() {

        return redisService.get(HELLO_WORLD_COUNTER_KEY)
                .map(o -> (HelloWorldCounterDto) o)
                .map(HelloWorldCounterDto::counter)
                .orElse(0);
    }
}
