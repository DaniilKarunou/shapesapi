package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;

public interface ShapeHandler {
    String getShapeType();
    ShapeEntity handle(ShapeRequest request);
}
