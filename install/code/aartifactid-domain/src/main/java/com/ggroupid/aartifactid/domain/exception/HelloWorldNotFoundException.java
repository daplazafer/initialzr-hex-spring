package com.ggroupid.aartifactid.domain.exception;

import lombok.Getter;

@Getter
public class HelloWorldNotFoundException extends RuntimeException {

    private final String languageCode;

    public HelloWorldNotFoundException(String languageCode) {
        super("Greeting greeting not found for language: " + languageCode);
        this.languageCode = languageCode;
    }

}