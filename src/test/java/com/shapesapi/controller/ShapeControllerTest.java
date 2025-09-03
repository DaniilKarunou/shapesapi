package com.shapesapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shapesapi.IntegrationTest;
import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.service.ShapeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
@AutoConfigureMockMvc
class ShapeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShapeService shapeService;

    @Test
    void shouldCreateShapeAndReturnIt() throws Exception {
        ShapeRequest request = new ShapeRequest("circle", List.of(10.0));

        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is("CIRCLE")));
    }

    @Test
    void shouldReturnShapesByType() throws Exception {
        shapeService.addShape(new ShapeRequest("square", List.of(5.0)));
        shapeService.addShape(new ShapeRequest("square", List.of(5.0)));
        shapeService.addShape(new ShapeRequest("circle", List.of(5.0)));

        mockMvc.perform(get("/api/v1/shapes/square")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].type", is("SQUARE")));
    }
}