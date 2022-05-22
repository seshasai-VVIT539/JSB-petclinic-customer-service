package com.springframework.samples.petclinic.customerservice.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
