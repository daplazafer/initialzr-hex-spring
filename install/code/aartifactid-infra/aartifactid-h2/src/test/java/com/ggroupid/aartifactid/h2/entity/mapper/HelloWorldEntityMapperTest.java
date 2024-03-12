package com.ggroupid.aartifactid.h2.entity.mapper;

import com.ggroupid.aartifactid.h2.entity.HelloWorldEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class HelloWorldEntityMapperTest {

    HelloWorldEntityMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(HelloWorldEntityMapper.class);
    }

    @Test
    void givenHelloWorldEntity_whenMap_thenReturnHelloWorld() {

        var entity = HelloWorldEntityMother.en();

        var result = mapper.map(entity);

        assertThat(result.greeting()).isEqualTo(entity.getMessage());
    }
}
