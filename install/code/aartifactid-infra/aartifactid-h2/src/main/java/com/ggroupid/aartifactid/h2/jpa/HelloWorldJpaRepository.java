package com.ggroupid.aartifactid.h2.jpa;

import com.ggroupid.aartifactid.h2.entity.HelloWorldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HelloWorldJpaRepository extends JpaRepository<HelloWorldEntity, Long> {

    Optional<HelloWorldEntity> findByLanguageCode(String languageCode);

}
