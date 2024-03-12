package com.ggroupid.aartifactid.api.controller;

import com.ggroupid.aartifactid.api.HelloWorldControllerApi;
import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import com.ggroupid.aartifactid.api.dto.mapper.HelloWorldDtoMapper;
import com.ggroupid.aartifactid.domain.usecase.HelloWorldUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
@RequiredArgsConstructor
public class HelloWorldController implements HelloWorldControllerApi {

    private final HelloWorldUseCase helloWorldUseCase;

    private final HelloWorldDtoMapper helloWorldDtoMapper;

    @GetMapping("/{languageCode}")
    @ResponseStatus(HttpStatus.OK)
    public HelloWorldDto helloWorld(@PathVariable String languageCode) {

        var helloWorld = helloWorldUseCase.execute(languageCode);

        return helloWorldDtoMapper.map(helloWorld);
    }
}
