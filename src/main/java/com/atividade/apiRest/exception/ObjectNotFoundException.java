package com.atividade.apiRest.exception;

public class ObjectNotFoundException extends RuntimeException {
	
	static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	

}
