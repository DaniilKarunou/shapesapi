package com.shapesapi.handler;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareHandlerTest {

    private SquareHandler squareHandler;

    @BeforeEach
    void setUp() {
        squareHandler = new SquareHandler();
    }

    @Test
    void shouldReturnCorrectShapeType() {
        assertEquals("SQUARE", squareHandler.getShapeType());
    }

    @Test
    void shouldHandleValidRequest() {
        ShapeRequest request = new ShapeRequest("SQUARE", List.of(4.0));

        ShapeEntity entity = squareHandler.handle(request);

        assertNotNull(entity);
        assertEquals("SQUARE", entity.getType());
        assertEquals(1, entity.getParameters().size());
        assertEquals(4.0, entity.getParameters().getFirst());
    }

    @Test
    void shouldThrowExceptionWhenInvalidParameters() {
        ShapeRequest request = new ShapeRequest("SQUARE", List.of(4.0, 5.0));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> squareHandler.handle(request));

        assertEquals("Square requires 1 parameter (side length).", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNoParameters() {
        ShapeRequest request = new ShapeRequest("SQUARE", List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> squareHandler.handle(request));

        assertEquals("Square requires 1 parameter (side length).", exception.getMessage());
    }
}
