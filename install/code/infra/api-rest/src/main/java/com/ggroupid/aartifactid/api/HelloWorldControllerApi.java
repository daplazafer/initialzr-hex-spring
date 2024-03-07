package com.ggroupid.aartifactid.api;

import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface HelloWorldControllerApi {

    @Operation(summary = "Gets HelloWorld",
            description = "Returns a Hello World! greeting",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The HelloWorld greeting",
                            content = @Content(schema = @Schema(implementation = HelloWorldDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            })
    HelloWorldDto helloWorld();

}
