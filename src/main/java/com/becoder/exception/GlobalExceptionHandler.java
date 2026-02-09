package com.becoder.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){
		log.error("GlobalExceptionHandler :: handelResourceNotFoundException :: "+e.getMessage());
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> fieldErrors = new HashMap<>();

	        ex.getBindingResult()
	          .getFieldErrors()
	          .forEach(error ->
	              fieldErrors.put(error.getField(), error.getDefaultMessage())
	          );

	        ValidationErrorResponse response = new ValidationErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                "Validation Failed",
	                fieldErrors
	        );

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	

}
