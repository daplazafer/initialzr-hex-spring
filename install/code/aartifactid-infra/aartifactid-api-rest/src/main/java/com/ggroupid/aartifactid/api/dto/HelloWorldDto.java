package com.ggroupid.aartifactid.api.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public record HelloWorldDto(

        @Schema(description = "Hello World message", example = "Hello World!")
        String message

) {
}
