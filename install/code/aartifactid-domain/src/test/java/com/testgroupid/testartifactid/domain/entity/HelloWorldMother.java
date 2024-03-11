package com.testgroupid.testartifactid.domain.entity;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;

public class HelloWorldMother {

    public static HelloWorld one() {
        return new HelloWorld("Hello, World!");
    }

    public static HelloWorld withCustomMessage(String message) {
        return new HelloWorld(message);
    }

}