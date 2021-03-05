package com.spbl.assignment1.exception;

public class BadRequestException extends Exception {
	
	public BadRequestException() {
        super("Invalid request body. Please try again.");
    }

    public BadRequestException(final String msg) {
        super(msg);
    }
    
}
