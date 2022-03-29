package com.example.demo.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.response.ExceptionResponse;


@ControllerAdvice
public class ApplicationExceptionHandler{
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<String, String>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException e, WebRequest request) {		
		ExceptionResponse error = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false), 
				HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}	
}

