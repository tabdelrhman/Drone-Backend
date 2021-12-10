package com.musala.test.drones.execption;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomiseResponseEntityExcepHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DroneValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleDroneValidationException(Exception ex, WebRequest request) {
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage()
				,HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage()
				,HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date()
				, ex.getMessage(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
