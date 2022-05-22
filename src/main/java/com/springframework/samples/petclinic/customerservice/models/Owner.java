package com.springframework.samples.petclinic.customerservice.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.springframework.core.style.ToStringCreator;

import antlr.collections.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "owners")
public class Owner {
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
