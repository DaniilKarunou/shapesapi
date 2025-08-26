package com.shapesapi.handler;

import com.shapesapi.IntegrationTest;
import com.shapesapi.dto.ShapeRequest;
import com.shapesapi.entity.ShapeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
class SquareHandlerTest {

    @Autowired
    private SquareHandler handler;

    @Test
    void shouldCreateSquare() {
        ShapeRequest request = new ShapeRequest("SQUARE", List.of(10.0));
        ShapeEntity entity = handler.handle(request);

        assertThat(entity.getParameters()).containsExactly(10.0);
    }

    @Test
    void shouldThrowWhenInvalidParams() {
        ShapeRequest request = new ShapeRequest("SQUARE", List.of(10.0, 5.0));
        assertThrows(IllegalArgumentException.class, () -> handler.handle(request));
    }
}
