package com.ggroupid.aartifactid.redis.adapter;

import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import com.ggroupid.aartifactid.h2.HelloWorldCounterDto;
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

        var helloWorldCounter = redisService.get(HELLO_WORLD_COUNTER_KEY);

        return Objects.nonNull(helloWorldCounter) ? ((HelloWorldCounterDto) helloWorldCounter).counter() : 0;
    }
}
