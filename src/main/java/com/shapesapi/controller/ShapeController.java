package com.shapesapi.controller;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.dto.ShapeResponse;
import com.shapesapi.service.ShapeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shapes")
@RequiredArgsConstructor
public class ShapeController {

    private final ShapeService service;

    @PostMapping
    public ShapeResponse createShape(@Valid @RequestBody ShapeRequest request) {
        return service.addShape(request);
    }

    @GetMapping("/{type}")
    public List<ShapeResponse> getShapes(@PathVariable String type) {
        return service.getShapesByType(type);
    }
}
