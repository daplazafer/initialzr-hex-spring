package com.ggroupid.aartifactid.h2;

public class HelloWorldCounterDtoMother {

    public static HelloWorldCounterDto one() {
        return new HelloWorldCounterDto(0);
    }

    public static HelloWorldCounterDto withCustomValue(int value) {
        return new HelloWorldCounterDto(value);
    }

}