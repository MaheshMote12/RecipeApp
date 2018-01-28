package com.me.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations=Controller.class)
public class ControllerExceptionHandler {

	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=NumberFormatException.class)
	public ModelAndView handleNumberFormatException(Exception exception){
		log.info("ExceptionHandler error method ");
		log.error(exception.getMessage() );
		
		ModelAndView view = new ModelAndView("400error");
		
		view.addObject("exception", exception);
		return view;
	}


	
}
