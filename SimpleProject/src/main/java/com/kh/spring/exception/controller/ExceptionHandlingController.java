package com.kh.spring.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.exception.InvalidParameterException;
import com.kh.spring.exception.MemberNotFoundException;
import com.kh.spring.exception.TooLageValueException;
import com.kh.spring.exception.PasswordNotmathchException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

	private ModelAndView createErrorResponse(String errorMsg, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", e.getMessage())
		  .setViewName("include/error_page"); 
		return mv; 
		
		
		
	}
	
	@ExceptionHandler(InvalidParameterException.class) 
	protected ModelAndView invalidParameterError(InvalidParameterException e) {
		return createErrorResponse(e.getMessage(), e);
	}
	 
	
	
	@ExceptionHandler(TooLageValueException.class) 
	protected ModelAndView TooLageValueException(TooLageValueException e) {
		return createErrorResponse(e.getMessage(), e);
	}
	 
	@ExceptionHandler(MemberNotFoundException.class) 
	protected ModelAndView MemberNotFoundException(MemberNotFoundException e) {
		return createErrorResponse(e.getMessage(), e);
	}
	
	@ExceptionHandler(PasswordNotmathchException.class) 
	protected ModelAndView PasswordNotmathchException(PasswordNotmathchException e) {
		return createErrorResponse(e.getMessage(), e);
		
	}
	

	
	/*
	 * @ExceptionHandler(InvalidParameterException.class) protected ModelAndView
	 * invalidParameterError(InvalidParameterException e) { ModelAndView mv = new
	 * ModelAndView(); mv.addObject("message", e.getMessage())
	 * .setViewName("include/error_page"); return mv; }
	 * 
	 * 
	 * @ExceptionHandler(TooLageValueException.class) protected ModelAndView
	 * runTimeError(TooLageValueException e) { ModelAndView mv = new ModelAndView();
	 * mv.addObject("message",e.getMessage()) .setViewName("include/error_page");
	 * return mv; }
	 */
	
}
