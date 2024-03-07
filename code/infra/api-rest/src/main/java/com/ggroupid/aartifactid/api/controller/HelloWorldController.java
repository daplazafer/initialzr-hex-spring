package com.ggroupid.aartifactid.api.controller;

import com.ggroupid.aartifactid.api.HelloWorldControllerApi;
import com.ggroupid.aartifactid.api.dto.HelloWorldDTO;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@RequiredArgsConstructor
public class HelloWorldController implements HelloWorldControllerApi {

    private final HelloWorldUseCase helloWorldUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public HelloWorldDTO helloWorld() {

        var helloWorld = helloWorldUseCase.getHelloWorld();

        return new HelloWorldDTO(helloWorld.message());
    }

}
