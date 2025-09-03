package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleHandlerTest {

    private RectangleHandler rectangleHandler;

    @BeforeEach
    void setUp() {
        rectangleHandler = new RectangleHandler();
    }

    @Test
    void shouldReturnCorrectShapeType() {
        assertEquals("RECTANGLE", rectangleHandler.getShapeType());
    }

    @Test
    void shouldHandleValidRequest() {
        ShapeRequest request = new ShapeRequest("RECTANGLE", List.of(4.0, 5.0));

        ShapeEntity entity = rectangleHandler.handle(request);

        assertNotNull(entity);
        assertEquals("RECTANGLE", entity.getType());
        assertEquals(2, entity.getParameters().size());
        assertEquals(4.0, entity.getParameters().get(0));
        assertEquals(5.0, entity.getParameters().get(1));
    }

    @Test
    void shouldThrowExceptionWhenTooManyParameters() {
        ShapeRequest request = new ShapeRequest("RECTANGLE", List.of(4.0, 5.0, 6.0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> rectangleHandler.handle(request));

        assertEquals("Rectangle requires 2 parameters (width, height).", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTooFewParameters() {
        ShapeRequest request = new ShapeRequest("RECTANGLE", List.of(4.0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> rectangleHandler.handle(request));

        assertEquals("Rectangle requires 2 parameters (width, height).", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoParameters() {
        ShapeRequest request = new ShapeRequest("RECTANGLE", List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> rectangleHandler.handle(request));

        assertEquals("Rectangle requires 2 parameters (width, height).", exception.getMessage());
    }
}
