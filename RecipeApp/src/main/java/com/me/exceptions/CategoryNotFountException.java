package com.me.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class CategoryNotFountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4072775780375638700L;

	public CategoryNotFountException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
	
}
