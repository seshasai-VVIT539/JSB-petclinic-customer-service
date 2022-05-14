package com.springframework.samples.petclinic.customerservice.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "pets")
@Data
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType type;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnore
	private Customer owner;

	@Override
	public String toString() {
		return "id"+ this.getId()).append("name", this.getName())
				.append("birthDate", this.getBirthDate()).append("type", this.getType().getName())
				.append("ownerFirstname", this.getOwner().getFirstName())
				.append("ownerLastname", this.getOwner().getLastName()).toString();
	}
}
