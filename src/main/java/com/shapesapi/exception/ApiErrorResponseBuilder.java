package com.shapesapi.exception;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@UtilityClass
public class ApiErrorResponseBuilder {

    public static ApiErrorResponse build(HttpStatus status, String error, Object details) {
        return ApiErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .error(error)
                .details(details)
                .build();
    }

    public static ApiErrorResponse badRequest(String error, Object details) {
        return build(HttpStatus.BAD_REQUEST, error, details);
    }

    public static ApiErrorResponse notFound(String error, Object details) {
        return build(HttpStatus.NOT_FOUND, error, details);
    }

    public static ApiErrorResponse internalServerError(String error, Object details) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, error, details);
    }
}
