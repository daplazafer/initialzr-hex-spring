package com.ggroupid.aartifactid.api.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public record HelloWorldDto(

        @Schema(description = "Hello World greeting", example = "Hello World!")
        String greeting

) {
}
