package com.springframework.samples.petclinic.customerservice.utils;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PetRequest {
	private int id;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@Size(min = 1)
	private String name;

	private int typeId;
}
