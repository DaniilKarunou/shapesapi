package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.springframework.stereotype.Component;

@Component
public class CircleHandler implements ShapeHandler {

    @Override
    public ShapeEntity handle(ShapeRequest request) {
        if (request.parameters().size() != 1) {
            throw new IllegalArgumentException("Circle requires 1 parameter (radius).");
        }
        return ShapeEntity.builder()
                .type(getShapeType())
                .parameters(request.parameters())
                .build();
    }

    @Override
    public String getShapeType() {
        return "CIRCLE";
    }
}
