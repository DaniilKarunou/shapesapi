package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.springframework.stereotype.Component;

@Component
public class SquareHandler implements ShapeHandler {

    @Override
    public String getShapeType() {
        return "SQUARE";
    }

    @Override
    public ShapeEntity handle(ShapeRequest request) {
        if (request.parameters().size() != 1) {
            throw new IllegalArgumentException("Square requires 1 parameter (side length).");
        }
        return ShapeEntity.builder()
                .type(getShapeType())
                .parameters(request.parameters())
                .build();
    }
}
