package com.ggroupid.aartifactid.core.usecase;

import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HelloWorldUseCaseImplTest {

    @Mock
    HelloWorldCounterPort helloWorldCounter;

    @InjectMocks
    HelloWorldUseCaseImpl useCase;

    @Test
    void whenGetHelloWorld_thenReturnHelloWorldWithMessage() {

        given(helloWorldCounter.getCounter()).willReturn(0);

        var result = useCase.execute();

        then(helloWorldCounter).should(times(1)).increment();
        assertThat(result.message()).isEqualTo("Hello World!");
    }
}
