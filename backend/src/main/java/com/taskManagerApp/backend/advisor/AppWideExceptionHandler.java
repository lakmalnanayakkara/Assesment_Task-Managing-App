package com.taskManagerApp.backend.advisor;

import com.taskManagerApp.backend.dto.StandardResponse;
import com.taskManagerApp.backend.exception.AlreadyExistException;
import com.taskManagerApp.backend.exception.BadCredentialsException;
import com.taskManagerApp.backend.exception.NotFoundException;
import com.taskManagerApp.backend.exception.ValidationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<StandardResponse> handleAlreadyExistException(AlreadyExistException e) {
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(409,"ERROR",e.getMessage()),
                HttpStatus.CONFLICT
        );
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e) {
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(404,"ERROR",e.getMessage()),
                HttpStatus.NOT_FOUND
        );
        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardResponse> handleBadCredentialsException(BadCredentialsException e) {
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(400,"ERROR",e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
        return response;
    }

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<StandardResponse> handleValidationFailedException(ValidationFailedException e) {
        ResponseEntity<StandardResponse> response = new ResponseEntity<>(
                new StandardResponse(400,"ERROR",e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
        return response;
    }
}
