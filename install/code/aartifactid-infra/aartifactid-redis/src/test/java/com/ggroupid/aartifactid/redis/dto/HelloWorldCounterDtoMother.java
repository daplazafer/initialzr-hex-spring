package com.ggroupid.aartifactid.redis.dto;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;

public class HelloWorldCounterDtoMother {

    public static HelloWorldCounterDto one() {
        return new HelloWorldCounterDto(0);
    }

    public static HelloWorldCounterDto withCustomValue(int value) {
        return new HelloWorldCounterDto(value);
    }

}