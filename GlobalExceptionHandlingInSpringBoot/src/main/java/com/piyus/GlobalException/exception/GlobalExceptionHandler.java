package com.piyus.GlobalException.exception;

import com.piyus.GlobalException.dto.ExceptionResponseDTO;
import com.piyus.GlobalException.dto.ValidationExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO>handleResourceNotFoundException(ResourceNotFoundException rx, HttpServletRequest request){


        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                rx.getMessage(),

                // getting from HttpServletRequest - it has a path information
                request.getRequestURI()

        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponseDTO);

    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDTO>handleDuplicateResourceException(DuplicateResourceException rx, HttpServletRequest request){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                rx.getMessage(),

                // getting from HttpServletRequest - it has a path information
                request.getRequestURI()

        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponseDTO);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO>handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request){

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));


        ValidationExceptionResponseDTO exceptionResponse = new ValidationExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "validation failed",
                // getting from HttpServletRequest - it has a path information
                request.getRequestURI(),
                fieldErrors

        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDTO>handleRuntimeException(RuntimeException rx, HttpServletRequest request){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                rx.getMessage(),

                // getting from HttpServletRequest - it has a path information
                request.getRequestURI()

        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDTO);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO>handleGenericException(Exception rx, HttpServletRequest request){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                rx.getMessage(),

                // getting from HttpServletRequest - it has a path information
                request.getRequestURI()

        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponseDTO);

    }


}
