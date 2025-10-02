package com.example.backend.Exceptions;

import java.time.OffsetDateTime;

import lombok.Getter;

@Getter
public class ApiError {

    private int status;
    private String message;
    private OffsetDateTime timestamp;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = OffsetDateTime.now();
    }

}
