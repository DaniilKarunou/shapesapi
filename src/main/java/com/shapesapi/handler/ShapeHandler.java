package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;

public interface ShapeHandler {
    ShapeEntity handle(ShapeRequest request);
    String getShapeType();
}
