package com.oumana.exceptions;

public class APIException extends RuntimeException{
	private String error; 
	
	public APIException(String message, String error) {
		super(message + error);
	}
	
}
