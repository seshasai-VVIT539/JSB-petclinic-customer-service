package com.springframework.samples.petclinic.customerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springframework.samples.petclinic.customerservice.exception.ExceptionMessage;
import com.springframework.samples.petclinic.customerservice.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleResourceNotFoundException(ResourceNotFoundException e) {
		return new ResponseEntity<ExceptionMessage>(
				new ExceptionMessage(e.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
	}
}
