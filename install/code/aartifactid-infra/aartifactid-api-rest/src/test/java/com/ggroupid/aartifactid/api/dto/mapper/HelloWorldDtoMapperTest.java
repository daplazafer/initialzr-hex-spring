package com.ggroupid.aartifactid.api.dto.mapper;

import com.ggroupid.aartifactid.api.dto.mapper.HelloWorldDtoMapper;
import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class HelloWorldDtoMapperTest {

    HelloWorldDtoMapper mapper;

    HelloWorld helloWorld;


    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(HelloWorldDtoMapper.class);
        helloWorld = new HelloWorld("test");
    }

    @Test
    void shouldMapHelloWorldToHelloWorldDto() {
        var result = mapper.map(helloWorld);

        assertThat(helloWorld.message()).isEqualTo(result.message());
    }
}
