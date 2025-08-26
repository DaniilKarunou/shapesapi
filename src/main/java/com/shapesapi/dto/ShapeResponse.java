package com.shapesapi.dto;

import java.util.List;

public record ShapeResponse(
        Long id,
        String type,
        List<Double> parameters
) {}