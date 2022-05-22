package com.springframework.samples.petclinic.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.petclinic.customerservice.models.Pet;
import com.springframework.samples.petclinic.customerservice.models.PetType;
import com.springframework.samples.petclinic.customerservice.service.PetService;
import com.springframework.samples.petclinic.customerservice.utils.PetDetails;
import com.springframework.samples.petclinic.customerservice.utils.PetRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PetController {

	@Autowired
	private PetService petService;

	@GetMapping("/petTypes")
	public List<PetType> getPetTypes() {
		return petService.getPetTypes();
	}

	@PostMapping("/owners/{ownerId}/pets")
	@ResponseStatus(HttpStatus.CREATED)
	public PetDetails addPet(@PathVariable int ownerId, @RequestBody PetRequest petRequest) {
		Pet pet = petService.addPetProcess(petRequest, ownerId);
		return new PetDetails(pet);
	}

	@PutMapping("/owners/{ownerId}/pets")
	public void updatePet(@PathVariable int ownerId, @RequestBody PetRequest petRequest) {
		log.info(petRequest.toString());
		petService.updatePetProcess(petRequest, ownerId);
	}

	@GetMapping("/owners/{ownerId}/pets/{petId}")
	public PetDetails findPetByIdOfOwnerById(@PathVariable int ownerId, @PathVariable int petId) {
		return new PetDetails(petService.findById(petId, ownerId));
	}
}
