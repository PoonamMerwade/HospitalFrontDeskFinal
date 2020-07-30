package com.cts.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SpecialistException extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> specialist = new ArrayList<>();
        specialist.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Specialist Not Found", specialist);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(SpecialistNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(SpecialistNotFoundException ex, WebRequest request) {
        List<String> specialist = new ArrayList<>();
        specialist.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Specialist Not Found", specialist);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> specialist = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
        	specialist.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", specialist);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
