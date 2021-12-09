package com.musala.test.drones.execption;

import java.util.Date;

public class ExceptionResponse {

	Date timeStamp;
	String message;
	String desc;
	
	public ExceptionResponse(Date timeStamp, String message, String desc) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
