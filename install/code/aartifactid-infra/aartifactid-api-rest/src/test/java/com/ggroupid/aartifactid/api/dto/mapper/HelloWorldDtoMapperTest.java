package com.ggroupid.aartifactid.api.dto.mapper;

import com.ggroupid.aartifactid.domain.entity.HelloWorldMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class HelloWorldDtoMapperTest {

    HelloWorldDtoMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(HelloWorldDtoMapper.class);
    }

    @Test
    void givenHelloWorld_whenMap_thenReturnHelloWorldDto() {

        var helloWorld = HelloWorldMother.en();

        var result = mapper.map(helloWorld);

        assertThat(result.message()).isEqualTo(helloWorld.message());
    }
}
