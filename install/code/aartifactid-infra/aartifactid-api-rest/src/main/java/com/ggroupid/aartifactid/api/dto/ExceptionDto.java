package com.ggroupid.aartifactid.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record ExceptionDto(

        @Schema(description = "Exception greeting", example = "Error in aartifactid")
        String message,

        @Schema(description = "Timestamp", example = "2033-03-33T03:33:33.330330330Z")
        Instant timestamp

) {
}
