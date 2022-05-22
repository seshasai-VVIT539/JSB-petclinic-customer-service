package com.springframework.samples.petclinic.customerservice.exception;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ExceptionMessage {
	private String msg;

	private LocalDate localDate;

	private String status;


	public ExceptionMessage(String msg, String status) {
		this.msg = msg;
		this.status = status;
		this.localDate = LocalDate.now();
	}
}
