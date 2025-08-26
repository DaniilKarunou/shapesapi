package com.shapesapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShapeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAndFetchSquare() throws Exception {
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        { "type": "SQUARE", "parameters": [10.0] }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("SQUARE"));

        mockMvc.perform(get("/api/v1/shapes/SQUARE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].parameters[0]").value(10.0));
    }
}