package com.shapesapi.service;

import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.dto.ShapeResponse;
import com.shapesapi.entity.ShapeEntity;
import com.shapesapi.handler.ShapeHandler;
import com.shapesapi.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShapeServiceTest {

    @Mock
    private ShapeRepository repository;

    @Mock
    private ShapeHandler circleHandler;

    private ShapeService shapeService;

    @BeforeEach
    void setUp() {
        when(circleHandler.getShapeType()).thenReturn("CIRCLE");
        shapeService = new ShapeService(repository, List.of(circleHandler));
    }

    @Test
    void shouldAddShapeSuccessfully() {
        ShapeRequest request = new ShapeRequest("CIRCLE", List.of(5.0));
        ShapeEntity entity = ShapeEntity.builder()
                .type("CIRCLE")
                .parameters(request.parameters())
                .id(1L)
                .build();

        when(circleHandler.handle(request)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);

        ShapeResponse response = shapeService.addShape(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("CIRCLE", response.type());
        assertEquals(request.parameters(), response.parameters());

        verify(circleHandler, times(1)).handle(request);
        verify(repository, times(1)).save(entity);
    }

    @Test
    void shouldThrowExceptionForUnknownShape() {
        ShapeRequest request = new ShapeRequest("TRIANGLE", List.of(3.0, 4.0, 5.0));

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> shapeService.addShape(request));

        assertEquals("Unknown shape type: TRIANGLE", exception.getMessage());
    }

    @Test
    void shouldReturnShapesByType() {
        ShapeEntity entity1 = ShapeEntity.builder()
                .id(1L)
                .type("CIRCLE")
                .parameters(List.of(5.0))
                .build();

        ShapeEntity entity2 = ShapeEntity.builder()
                .id(2L)
                .type("CIRCLE")
                .parameters(List.of(10.0))
                .build();

        when(repository.findAllByType("CIRCLE")).thenReturn(List.of(entity1, entity2));

        List<ShapeResponse> responses = shapeService.getShapesByType("CIRCLE");

        assertEquals(2, responses.size());
        assertEquals(1L, responses.get(0).id());
        assertEquals(10.0, responses.get(1).parameters().getFirst());
    }
}
