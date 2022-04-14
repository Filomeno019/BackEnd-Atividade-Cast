package com.atividade.apiRest.exception;

public class TransactionSystemException extends RuntimeException {
	
	static final long serialVersionUID = 1L;

	public TransactionSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionSystemException(String message) {
		super(message);
	}
	
	

}
