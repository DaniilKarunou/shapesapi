package com.shapesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fe ->
                                Optional.ofNullable(fe.getDefaultMessage())
                                        .orElse("Validation error"),
                        (existing, replacement) -> existing
                ));

        return ResponseEntity.badRequest().body(
                ApiErrorResponseBuilder.badRequest("Validation failed", fieldErrors)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(
                ApiErrorResponseBuilder.badRequest("Invalid argument", ex.getMessage())
        );
    }

    @ExceptionHandler(ShapeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ShapeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiErrorResponseBuilder.notFound("Shape not found", ex.getMessage())
        );
    }
}
