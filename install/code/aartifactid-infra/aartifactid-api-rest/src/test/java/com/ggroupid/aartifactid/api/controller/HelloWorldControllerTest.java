package com.ggroupid.aartifactid.api.controller;

import com.ggroupid.aartifactid.api.dto.mapper.HelloWorldDtoMapper;
import com.ggroupid.aartifactid.api.dto.mapper.HelloWorldDtoMother;
import com.ggroupid.aartifactid.domain.entity.HelloWorldMother;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
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

    @Test
    void givenLanguageCode_whenGetHelloWorld_thenReturnHelloWorld() {

        given(helloWorldUseCase.execute("en")).willReturn(HelloWorldMother.en());
        given(helloWorldDtoMapper.map(eq(HelloWorldMother.en()))).willReturn(HelloWorldDtoMother.en());

        var result = controller.helloWorld("en");

        then(helloWorldUseCase).should(times(1)).execute(any());
        then(helloWorldDtoMapper).should(times(1)).map(any());
        assertThat(result.message()).isEqualTo(HelloWorldDtoMother.en().message());
    }
}