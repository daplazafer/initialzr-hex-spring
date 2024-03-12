package com.ggroupid.aartifactid.domain.repository;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;

import java.util.Optional;

public interface HelloWorldRepository {

    Optional<HelloWorld> getByLanguage(String languageCode);

}
