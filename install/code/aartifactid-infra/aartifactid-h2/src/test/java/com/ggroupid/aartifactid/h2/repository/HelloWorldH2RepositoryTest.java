package com.ggroupid.aartifactid.h2.repository;

import com.ggroupid.aartifactid.domain.entity.HelloWorldMother;
import com.ggroupid.aartifactid.h2.entity.HelloWorldEntityMother;
import com.ggroupid.aartifactid.h2.entity.mapper.HelloWorldEntityMapper;
import com.ggroupid.aartifactid.h2.jpa.HelloWorldJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HelloWorldH2RepositoryTest {

    @Mock
    HelloWorldJpaRepository helloWorldJpaRepository;

    @Mock
    HelloWorldEntityMapper helloWorldEntityMapper;

    @InjectMocks
    HelloWorldH2Repository repository;

    @Test
    void givenHelloWorld_whenGetHelloWorld_thenReturnHelloWorld() {

        given(helloWorldJpaRepository.findByLanguageCode("en")).willReturn(Optional.of(HelloWorldEntityMother.en()));
        given(helloWorldEntityMapper.map(HelloWorldEntityMother.en())).willReturn(HelloWorldMother.en());

        var result = repository.getByLanguage("en");

        then(helloWorldJpaRepository).should(times(1)).findByLanguageCode(any());
        then(helloWorldEntityMapper).should(times(1)).map(any());
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().greeting()).isEqualTo(HelloWorldMother.en().greeting());
    }
}