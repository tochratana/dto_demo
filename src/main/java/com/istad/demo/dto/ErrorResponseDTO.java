package com.istad.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ErrorResponseDTO {
    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private List<String> validationErrors;

    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
    }
    public ErrorResponseDTO(int status, String error, String message) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
    }

}

