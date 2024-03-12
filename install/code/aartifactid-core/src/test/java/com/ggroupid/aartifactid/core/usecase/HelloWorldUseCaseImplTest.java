package com.ggroupid.aartifactid.core.usecase;

import com.ggroupid.aartifactid.domain.entity.HelloWorldMother;
import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import com.ggroupid.aartifactid.domain.repository.HelloWorldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HelloWorldUseCaseImplTest {

    @Mock
    HelloWorldCounterPort helloWorldCounter;

    @Mock
    HelloWorldRepository helloWorldRepository;

    @InjectMocks
    HelloWorldUseCaseImpl useCase;

    @Test
    void whenGetHelloWorld_thenReturnHelloWorldWithMessage() {

        given(helloWorldCounter.getCounter()).willReturn(0);
        given(helloWorldRepository.getByLanguage("en")).willReturn(Optional.of(HelloWorldMother.en()));

        var result = useCase.execute("en");

        then(helloWorldCounter).should(times(1)).increment();
        then(helloWorldRepository).should(times(1)).getByLanguage("en");
        assertThat(result.message()).isEqualTo(HelloWorldMother.en().message());
    }
}
