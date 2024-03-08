package com.ggroupid.aartifactid.api.controller;

import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import com.ggroupid.aartifactid.api.mapper.HelloWorldMapper;
import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HelloWorldControllerTest {

    @Mock
    HelloWorldUseCase helloWorldUseCase;

    @Mock
    HelloWorldMapper helloWorldMapper;

    @InjectMocks
    HelloWorldController controller;

    HelloWorld helloWorld;

    HelloWorldDto helloWorldDto;

    @BeforeEach
    void setUp() {
        helloWorld = new HelloWorld("test");
        helloWorldDto = new HelloWorldDto(helloWorld.message());
    }

    @DisplayName("helloWorld should return HelloWorldDto with message")
    @Test
    void getHelloWorldShouldReturnHelloWorldWithMessage() {

        when(helloWorldUseCase.getHelloWorld()).thenReturn(helloWorld);
        when(helloWorldMapper.map(eq(helloWorld))).thenReturn(helloWorldDto);

        var result = controller.helloWorld();

        assertThat(helloWorld.message()).isEqualTo(result.message());
    }
}