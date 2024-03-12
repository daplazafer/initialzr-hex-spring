package com.ggroupid.aartifactid.redis.adapter;

import com.ggroupid.aartifactid.h2.HelloWorldCounterDtoMother;
import com.ggroupid.aartifactid.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HelloWorldCounterAdapterTest {

    @Mock
    RedisService redisService;

    @InjectMocks
    HelloWorldCounterAdapter helloWorldCounter;

    @Test
    void givenCounterWithValue_whenGetCounter_thenReturnCounterWithThatValue() {

        given(redisService.get("hello-world")).willReturn(HelloWorldCounterDtoMother.withCustomValue(5));

        var result = helloWorldCounter.getCounter();

        then(redisService).should(times(1)).get("hello-world");
        assertThat(result).isEqualTo(5);
    }

    @Test
    void givenCounterWithValue_whenIncrement_thenIncrementCounter() {

        given(redisService.get("hello-world")).willReturn(HelloWorldCounterDtoMother.withCustomValue(0));

        helloWorldCounter.increment();

        then(redisService).should(times(1)).store("hello-world", HelloWorldCounterDtoMother.withCustomValue(1));
    }
}