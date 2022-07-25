package com.codewithsubham.exception.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(BookAPIException.class)
	public ResponseEntity<?> handleBookAPIException(BookAPIException bookAPIException) {
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("error message", bookAPIException.getMessage());
		errorMap.put("status", HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(errorMap , HttpStatus.BAD_REQUEST);
	}

}
