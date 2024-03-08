package com.testgroupid.testartifactid.core.usecase;

import com.ggroupid.aartifactid.core.usecase.HelloWorldUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class HelloWorldUseCaseImplTest {

    @InjectMocks
    HelloWorldUseCaseImpl useCase;

    @DisplayName("getHelloWorld should return HelloWorld with message")
    @Test
    void getHelloWorldShouldReturnHelloWorldWithMessage() {

        var result = useCase.getHelloWorld();

        assertThat(result.message()).isEqualTo("Hello World!");
    }
}
