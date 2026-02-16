package com.becoder.exception;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.becoder.util.CommonUtill;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e){
		//return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return CommonUtill.createErrorResponseMessage(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e){
		log.error("GlobalExceptionHandler :: handelResourceNotFoundException :: "+e.getMessage());
	//	return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		return CommonUtill.createErrorResponseMessage(e.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ExistDataException.class)
	public ResponseEntity<?> handleExistDataException(ExistDataException e){
		//return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
		return CommonUtill.createErrorResponseMessage(e.getMessage(),HttpStatus.CONFLICT);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		//return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		return CommonUtill.createErrorResponseMessage(e.getMessage(),HttpStatus.BAD_REQUEST);
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
	 @ExceptionHandler(FileNotFoundException.class)
		public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException e){
			//return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			return CommonUtill.createErrorResponseMessage(e.getMessage(),HttpStatus.NOT_FOUND);
		}

}
