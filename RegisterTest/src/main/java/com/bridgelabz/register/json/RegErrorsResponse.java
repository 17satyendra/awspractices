package com.bridgelabz.register.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class RegErrorsResponse extends Response
{
	List<FieldError> errors = new ArrayList<>();

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "RegErrorsResponse [errors=" + errors + "]";
	}
	
}
