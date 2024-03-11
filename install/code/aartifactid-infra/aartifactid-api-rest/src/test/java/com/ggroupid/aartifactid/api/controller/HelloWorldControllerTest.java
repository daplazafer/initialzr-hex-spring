package com.ggroupid.aartifactid.api.controller;

import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import com.ggroupid.aartifactid.api.dto.mapper.HelloWorldDtoMapper;
import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HelloWorldControllerTest {

    @Mock
    HelloWorldUseCase helloWorldUseCase;

    @Mock
    HelloWorldDtoMapper helloWorldDtoMapper;

    @InjectMocks
    HelloWorldController controller;

    HelloWorld helloWorld;

    HelloWorldDto helloWorldDto;

    @BeforeEach
    void setUp() {
        helloWorld = new HelloWorld("test");
        helloWorldDto = new HelloWorldDto(helloWorld.message());
    }

    @Test
    void givenHelloWorld_whenGetHelloWorld_thenReturnHelloWorld() {

        given(helloWorldUseCase.execute()).willReturn(helloWorld);
        given(helloWorldDtoMapper.map(eq(helloWorld))).willReturn(helloWorldDto);

        var result = controller.helloWorld();

        then(helloWorldUseCase).should(times(1)).execute();
        then(helloWorldDtoMapper).should(times(1)).map(any());
        assertThat(helloWorld.message()).isEqualTo(result.message());
    }
}