package com.ggroupid.aartifactid.api.mapper;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldMapperTest {

    HelloWorldMapper mapper;

    HelloWorld helloWorld;


    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(HelloWorldMapper.class);
        helloWorld = new HelloWorld("test");
    }

    @Test
    void shouldMapHelloWorldToHelloWorldDto() {
        var result = mapper.map(helloWorld);

        assertEquals(helloWorld.message(), result.message());
    }
}
