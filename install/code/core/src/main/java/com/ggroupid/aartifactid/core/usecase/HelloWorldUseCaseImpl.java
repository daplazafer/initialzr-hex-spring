package com.ggroupid.aartifactid.core.usecase;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldUseCaseImpl implements HelloWorldUseCase {

    @Override
    public HelloWorld getHelloWorld() {
        return new HelloWorld("Hello World!");
    }
}
