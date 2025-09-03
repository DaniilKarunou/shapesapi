package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.springframework.stereotype.Component;

@Component
public class RectangleHandler implements ShapeHandler {

    @Override
    public ShapeEntity handle(ShapeRequest request) {
        if (request.parameters().size() != 2) {
            throw new IllegalArgumentException("Rectangle requires 2 parameters (width, height).");
        }
        return ShapeEntity.builder()
                .type(getShapeType())
                .parameters(request.parameters())
                .build();
    }

    @Override
    public String getShapeType() {
        return "RECTANGLE";
    }
}
