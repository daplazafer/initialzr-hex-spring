package com.ggroupid.aartifactid.api.mapper;

import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HelloWorldMapper {
    HelloWorldDto map(HelloWorld source);
}
