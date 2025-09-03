package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleHandlerTest {

    private CircleHandler circleHandler;

    @BeforeEach
    void setUp() {
        circleHandler = new CircleHandler();
    }

    @Test
    void shouldReturnCorrectShapeType() {
        assertEquals("CIRCLE", circleHandler.getShapeType());
    }

    @Test
    void shouldHandleValidRequest() {
        ShapeRequest request = new ShapeRequest("CIRCLE", List.of(5.0));

        ShapeEntity entity = circleHandler.handle(request);

        assertNotNull(entity);
        assertEquals("CIRCLE", entity.getType());
        assertEquals(1, entity.getParameters().size());
        assertEquals(5.0, entity.getParameters().getFirst());
    }

    @Test
    void shouldThrowExceptionWhenInvalidParameters() {
        ShapeRequest request = new ShapeRequest("CIRCLE", List.of(5.0, 10.0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> circleHandler.handle(request));

        assertEquals("Circle requires 1 parameter (radius).", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoParameters() {
        ShapeRequest request = new ShapeRequest("CIRCLE", List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> circleHandler.handle(request));

        assertEquals("Circle requires 1 parameter (radius).", exception.getMessage());
    }
}