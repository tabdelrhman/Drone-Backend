package com.musala.test.drones.execption;

import org.springframework.http.HttpStatus;

public class DroneValidationException extends RuntimeException {

    private String message;
    private Throwable cause;
    private HttpStatus status;

    public DroneValidationException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

	
}
