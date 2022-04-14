package com.atividade.apiRest.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestemp, Integer status, String error) {
		super(timestemp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldname, String message) {
		this.errors.add(new FieldMessage(fieldname, message));
	}
	
	

}
