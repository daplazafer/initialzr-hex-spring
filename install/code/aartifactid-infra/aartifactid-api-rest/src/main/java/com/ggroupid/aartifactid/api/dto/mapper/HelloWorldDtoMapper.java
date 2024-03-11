package com.ggroupid.aartifactid.api.dto.mapper;

import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import com.ggroupid.aartifactid.domain.entity.HelloWorld;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HelloWorldDtoMapper {

    HelloWorldDto map(HelloWorld source);
}
