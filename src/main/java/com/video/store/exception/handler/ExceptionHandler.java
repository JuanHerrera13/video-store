package com.video.store.exception.handler;

import com.video.store.exception.MovieAlreadyExistsException;
import com.video.store.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({MovieAlreadyExistsException.class})
    protected ResponseEntity<ErrorResponse> handleNotFoundException(MovieAlreadyExistsException exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST
                , exception.getMessage()
                , Instant.now())
                , HttpStatus.BAD_REQUEST);
    }
}
