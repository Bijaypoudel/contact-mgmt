package com.stone.exceptions;

public class EntityNotFoundException extends RuntimeException{

	private final static String message = "Entity not Found";
			
	public EntityNotFoundException() {
		super(message);
	}
}
