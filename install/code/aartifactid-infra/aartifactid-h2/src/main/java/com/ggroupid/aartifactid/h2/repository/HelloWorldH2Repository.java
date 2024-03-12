package com.ggroupid.aartifactid.h2.repository;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.domain.repository.HelloWorldRepository;
import com.ggroupid.aartifactid.h2.entity.mapper.HelloWorldEntityMapper;
import com.ggroupid.aartifactid.h2.jpa.HelloWorldJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HelloWorldH2Repository implements HelloWorldRepository {

    private final HelloWorldJpaRepository helloWorldJpaRepository;

    private final HelloWorldEntityMapper helloWorldEntityMapper;

    @Override
    public Optional<HelloWorld> getByLanguage(String languageCode) {

        return helloWorldJpaRepository.findByLanguageCode(languageCode)
                .map(helloWorldEntityMapper::map);
    }

}
