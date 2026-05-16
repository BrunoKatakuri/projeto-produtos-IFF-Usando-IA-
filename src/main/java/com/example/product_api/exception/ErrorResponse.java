package com.example.product_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private List<String> errors;

    public ErrorResponse(
            LocalDateTime timestamp,
            int status,
            List<String> errors
    ) {

        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(
            ProductNotFoundException ex
    ) {

        return new ErrorResponse(
                LocalDateTime.now(),
                404,
                List.of(ex.getMessage())
        );
    }
}
