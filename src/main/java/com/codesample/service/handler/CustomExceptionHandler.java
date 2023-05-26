package com.codesample.service.handler;

import com.codesample.dto.ApiExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiExceptionDTO> handleNotFoundExceptions(EntityNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildException(HttpStatus.NOT_FOUND, errorMessage));
    }

    private ApiExceptionDTO buildException(HttpStatus status, String errorMessage) {
        return new ApiExceptionDTO(status.value(), errorMessage);
    }
}
