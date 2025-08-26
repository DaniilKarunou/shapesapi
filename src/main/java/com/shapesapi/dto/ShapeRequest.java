package com.shapesapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record ShapeRequest(
        @NotBlank String type,
        @NotNull List<Double> parameters
) {}
