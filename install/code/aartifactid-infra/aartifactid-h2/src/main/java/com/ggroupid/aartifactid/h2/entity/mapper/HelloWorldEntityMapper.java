package com.ggroupid.aartifactid.h2.entity.mapper;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.h2.entity.HelloWorldEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HelloWorldEntityMapper {

    @Mapping(source = "message", target = "greeting")
    HelloWorld map(HelloWorldEntity source);

}
