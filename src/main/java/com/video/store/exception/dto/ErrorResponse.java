package com.video.store.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;
    private String message;
    private Instant timestamp;
}
