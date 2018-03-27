package com.bridgelabz.register.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController 
{
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> exceptionHandle(Exception exception)
	{
		return null;
		
	}
}
