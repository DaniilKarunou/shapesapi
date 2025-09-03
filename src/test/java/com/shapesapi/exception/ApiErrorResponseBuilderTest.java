package com.shapesapi.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorResponseBuilderTest {

    @Test
    void shouldBuildBadRequest() {
        String error = "Invalid input";
        Object details = "Some details";

        ApiErrorResponse response = ApiErrorResponseBuilder.badRequest(error, details);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals(error, response.getError());
        assertEquals(details, response.getDetails());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void shouldBuildNotFound() {
        String error = "Not found";
        Object details = null;

        ApiErrorResponse response = ApiErrorResponseBuilder.notFound(error, details);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals(error, response.getError());
        assertNull(response.getDetails());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void shouldBuildInternalServerError() {
        String error = "Server error";
        Object details = "Stack trace";

        ApiErrorResponse response = ApiErrorResponseBuilder.internalServerError(error, details);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals(error, response.getError());
        assertEquals(details, response.getDetails());
        assertNotNull(response.getTimestamp());
    }
}
