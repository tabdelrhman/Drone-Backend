package com.musala.test.drones.execption;

import java.util.Date;

public class ExceptionResponse {

	Date timeStamp;
	String message;
    int statusCode;
    
    public ExceptionResponse() {}
    
    
	public ExceptionResponse(Date timeStamp, String message, int statusCode) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatus(int statusCode) {
		this.statusCode = statusCode;
	}

	
	
}
