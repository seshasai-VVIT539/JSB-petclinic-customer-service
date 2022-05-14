package com.springframework.samples.petclinic.customerservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue
	@Getter
	private Integer id;

	@Column(name = "first_name")
	@Getter
	@Setter
	@NotBlank
	private String firstName;

	@Column(name = "last_name")
	@Getter
	@Setter
	@NotBlank
	private String lastName;

	@Column(name = "address")
	@Getter
	@Setter
	@NotBlank
	private String address;

	@Getter
	@Setter
	@Column(name = "city")
	@NotBlank
	private String city;

	@Getter
	@Setter
	@Column(name = "telephone")
	@NotBlank
	@Digits(fraction = 0, integer = 12)
	private String telephone;
}
