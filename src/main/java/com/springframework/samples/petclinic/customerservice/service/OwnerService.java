package com.springframework.samples.petclinic.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springframework.samples.petclinic.customerservice.controller.ResourceNotFoundException;
import com.springframework.samples.petclinic.customerservice.models.Owner;
import com.springframework.samples.petclinic.customerservice.repo.OwnerRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OwnerService {
	@Autowired
	private OwnerRepo ownerRepo;

	public Owner addOwner(Owner owner) {
		return ownerRepo.save(owner);
	}

	public Owner findOwner(int ownerId) {
		return ownerRepo.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Onwer " + ownerId + " not found"));
	}

	public List<Owner> findAll() {
		return ownerRepo.findAll();
	}

	public void updateOwner(int ownerId, Owner ownerRequest) {
		final Optional<Owner> owner = ownerRepo.findById(ownerId);
		final Owner ownerModel = owner
				.orElseThrow(() -> new ResourceNotFoundException("Owner " + ownerId + " not found"));
		ownerModel.setFirstName(ownerRequest.getFirstName());
		ownerModel.setLastName(ownerRequest.getLastName());
		ownerModel.setCity(ownerRequest.getCity());
		ownerModel.setAddress(ownerRequest.getAddress());
		ownerModel.setTelephone(ownerRequest.getTelephone());
		log.info("Saving owner {}", ownerModel);
		ownerRepo.save(ownerModel);

	}
}
