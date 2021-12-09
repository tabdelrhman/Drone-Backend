package com.musala.test.drones.execption;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomiseResponseEntityExcepHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DroneValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleDroneValidationException(Exception ex, WebRequest request) {
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage()
				, request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
	
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage()
				, request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
