package com.velocity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(NoSuchElementFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionResponse handlexception(NoSuchElementFoundException exception) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode("ERROR_EMPLOYEE");
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handlexception(Exception exception) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setErrorCode("ERROR_SERVER");
		exceptionResponse.setErrorMessage(exception.getMessage());
		return exceptionResponse;
	}
	
	

}
