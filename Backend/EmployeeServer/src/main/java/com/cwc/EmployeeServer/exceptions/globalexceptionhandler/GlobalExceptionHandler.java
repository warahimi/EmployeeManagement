package com.cwc.EmployeeServer.exceptions.globalexceptionhandler;

import com.cwc.EmployeeServer.exceptions.ErrorResponse;
import com.cwc.EmployeeServer.exceptions.customexceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // This class will handle exceptions globally for all controllers
     @ExceptionHandler(EmployeeNotFoundException.class)
     public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
         ErrorResponse errorResponse = new ErrorResponse();
         errorResponse.setMessage(ex.getMessage());
         errorResponse.setDate(LocalDate.now());
         errorResponse.setTime(LocalTime.now());
         errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
         errorResponse.setException(ex.getClass().getName());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
     }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // catch-all handler for unknown/unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("An unexpected error occurred.");
        errorResponse.setDate(LocalDate.now());
        errorResponse.setTime(LocalTime.now());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setException(ex.getClass().getName());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
