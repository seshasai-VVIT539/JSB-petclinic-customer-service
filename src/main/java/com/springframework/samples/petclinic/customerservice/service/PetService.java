package com.springframework.samples.petclinic.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.samples.petclinic.customerservice.controller.ResourceNotFoundException;
import com.springframework.samples.petclinic.customerservice.models.Owner;
import com.springframework.samples.petclinic.customerservice.models.Pet;
import com.springframework.samples.petclinic.customerservice.models.PetType;
import com.springframework.samples.petclinic.customerservice.repo.OwnerRepo;
import com.springframework.samples.petclinic.customerservice.repo.PetRepo;
import com.springframework.samples.petclinic.customerservice.utils.PetRequest;

import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

@Service
@Slf4j
public class PetService {
	@Autowired
	private PetRepo petRepo;

	@Autowired
	private OwnerRepo ownerRepo;

	public List<PetType> getPetTypes() {
		return petRepo.findPetTypes();
	}

	public Pet addPetProcess(PetRequest petRequest, int ownerId) {
		Optional<Owner> optionalOwner = ownerRepo.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new ResourceNotFoundException("Owner " + ownerId + " not found"));
		final Pet pet = new Pet();
		owner.addPet(pet);
		return save(pet, petRequest);
	}

	private Pet save(Pet pet, PetRequest petRequest) {
		pet.setName(petRequest.getName());
		pet.setBirthDate(petRequest.getBirthDate());
		petRepo.findPetTypeById(petRequest.getTypeId()).ifPresent(pet::setType);
		log.info("Saving pet {}", pet);
		return petRepo.save(pet);
	}

	public void updatePetProcess(PetRequest petRequest, int ownerId) {
		int petId = petRequest.getID();
		Pet pet = findById(petId, ownerId);
		if (pet.getOwner().getId() != ownerId) {
			throw new ResourceNotFoundException("Your pet with ID " + petId + " not found");
		}
		save(pet, petRequest);
	}

	public Pet findById(int petId, int ownerId) {
		Pet pet = findById(petId);
		if (pet.getOwner().getId() != ownerId) {
			throw new ResourceNotFoundException("Your pet with ID " + petId + " not found");
		}
		return pet;
	}

	private Pet findById(int petId) {
		Optional<Pet> pet = petRepo.findById(petId);
		if (!pet.isPresent()) {
			throw new ResourceNotFoundException("Pet " + petId + " not found");
		}
		return pet.get();
	}
}
