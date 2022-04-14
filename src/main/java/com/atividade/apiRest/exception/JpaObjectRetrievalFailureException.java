package com.atividade.apiRest.exception;


public class JpaObjectRetrievalFailureException  extends RuntimeException {
	
	static final long serialVersionUID = 1L;

	public JpaObjectRetrievalFailureException (String message, Throwable cause) {
		super(message, cause);
	}

	public JpaObjectRetrievalFailureException (String message) {
		super(message);
	}
	
	

}
