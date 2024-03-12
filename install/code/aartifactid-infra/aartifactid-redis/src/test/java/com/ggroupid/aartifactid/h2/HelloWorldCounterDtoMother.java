package com.ggroupid.aartifactid.h2;

import com.ggroupid.aartifactid.redis.dto.HelloWorldCounterDto;

public class HelloWorldCounterDtoMother {

    public static HelloWorldCounterDto one() {
        return new HelloWorldCounterDto(0);
    }

    public static HelloWorldCounterDto withCustomValue(int value) {
        return new HelloWorldCounterDto(value);
    }

}