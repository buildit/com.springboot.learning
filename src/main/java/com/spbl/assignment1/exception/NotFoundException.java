package com.spbl.assignment1.exception;

public class NotFoundException extends Exception {
	
	public NotFoundException() {
		super("Not found. Please try again");
	}

	public NotFoundException(String msg) {
		super(msg);
	}
	
}
