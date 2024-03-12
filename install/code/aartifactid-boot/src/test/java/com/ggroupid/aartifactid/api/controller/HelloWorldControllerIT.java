package com.ggroupid.aartifactid.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggroupid.aartifactid.api.dto.HelloWorldDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class HelloWorldControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void whenGetHelloWorldWithLanguageCode_thenReturnHelloWorldMessageForThatLanguage() throws Exception {

        var response = mockMvc.perform(MockMvcRequestBuilders.get("/hello-world/en")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        var result = mapper.readValue(response.getContentAsByteArray(), HelloWorldDto.class);

        assertThat(result.message()).isEqualTo("Hello World!");
    }

}
