package com.ggroupid.aartifactid.core.usecase;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.domain.port.HelloWorldCounterPort;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class HelloWorldUseCaseImpl implements HelloWorldUseCase {

    private static final String HELLO_WORLD_MESSAGE = "Hello World!";

    private final HelloWorldCounterPort helloWorldCounter;

    @Override
    public HelloWorld execute() {

        helloWorldCounter.increment();
        log.info("{} greetings sent", helloWorldCounter.getCounter());

        return new HelloWorld(HELLO_WORLD_MESSAGE);
    }
}


