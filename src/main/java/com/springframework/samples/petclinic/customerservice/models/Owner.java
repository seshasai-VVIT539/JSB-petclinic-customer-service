package com.springframework.samples.petclinic.customerservice.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owners")
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private Integer id;

	@Column(name = "first_name")
	@Getter
	@Setter
	@NotBlank(message = "firstName should not be blank")
	private String firstName;

	@Column(name = "last_name")
	@Getter
	@Setter
	@NotBlank(message = "lastName should not be blank")
	private String lastName;

	@Column(name = "address")
	@Getter
	@Setter
	@NotBlank(message = "address should not be blank")
	private String address;

	@Getter
	@Setter
	@Column(name = "city")
	@NotBlank(message = "city should not be blank")
	private String city;

	@Getter
	@Setter
	@Column(name = "telephone")
	@NotBlank(message = "telephone should not be blank")
	@Digits(fraction = 0, integer = 12)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "owner")
	private Set<Pet> pets;

	protected Set<Pet> getPetsInternal() {
		if (this.pets == null) {
			this.pets = new HashSet<Pet>();
		}
		return this.pets;
	}

	public List getPets() {
		final ArrayList<Pet> sortedPets = new ArrayList<Pet>(getPetsInternal());
		sortedPets.sort((peta, petb) -> peta.getName().compareTo(petb.getName()));
		return (List) Collections.unmodifiableList(sortedPets);

	}

	public void addPet(Pet pet) {
		getPetsInternal().add(pet);
		pet.setOwner(this);
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", this.getId()).append("lastName", this.getLastName())
				.append("firstName", this.getFirstName()).append("address", this.address).append("city", this.city)
				.append("telephone", this.telephone).toString();
	}
}
