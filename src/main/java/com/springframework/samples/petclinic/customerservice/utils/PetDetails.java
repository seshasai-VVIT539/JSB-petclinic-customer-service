package com.springframework.samples.petclinic.customerservice.utils;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.springframework.samples.petclinic.customerservice.models.Pet;
import com.springframework.samples.petclinic.customerservice.models.PetType;

import lombok.Data;

@Data
public class PetDetails {

	private long id;

	private String name;

	private String owner;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	private PetType type;

	public PetDetails(Pet pet) {
		this.id = pet.getId();
		this.name = pet.getName();
		this.owner = pet.getOwner().getFirstName() + " " + pet.getOwner().getLastName();
		this.birthDate = pet.getBirthDate();
		this.type = pet.getType();
	}
}
