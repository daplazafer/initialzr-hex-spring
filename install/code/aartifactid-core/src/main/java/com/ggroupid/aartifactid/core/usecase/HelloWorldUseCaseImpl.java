package com.ggroupid.aartifactid.core.usecase;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.domain.exception.HelloWorldNotFoundException;
import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import com.ggroupid.aartifactid.domain.repository.HelloWorldRepository;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class HelloWorldUseCaseImpl implements HelloWorldUseCase {

    private final HelloWorldCounterPort helloWorldCounter;

    private final HelloWorldRepository helloWorldRepository;

    @Override
    public HelloWorld execute(String languageCode) {

        var helloWorld = helloWorldRepository.getByLanguage(languageCode);

        if (helloWorld.isPresent()) {
            helloWorldCounter.increment();
            log.debug("{} greetings sent", helloWorldCounter.getCounter());
        }

        return helloWorld.orElseThrow(() -> new HelloWorldNotFoundException(languageCode));
    }

}


