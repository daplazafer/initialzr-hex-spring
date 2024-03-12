package com.ggroupid.aartifactid.h2.entity.mapper;

import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import com.ggroupid.aartifactid.h2.entity.HelloWorldEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HelloWorldEntityMapper {

    HelloWorld map(HelloWorldEntity source);

}
