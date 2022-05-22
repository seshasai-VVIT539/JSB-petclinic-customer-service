package com.springframework.samples.petclinic.customerservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springframework.samples.petclinic.customerservice.models.Owner;
import com.springframework.samples.petclinic.customerservice.service.OwnerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Owner createOwner(@Valid @RequestBody Owner owner) {
		return ownerService.addOwner(owner);
	}

	@GetMapping(value = "/{ownerId}")
	public Owner findOwner(@PathVariable("ownerId") @Min(1) int ownerId) {
		return ownerService.findOwner(ownerId);
	}

	@GetMapping
	public List<Owner> findAll() {
		return ownerService.findAll();
	}

	@PutMapping(value = "/{ownerId}")
	public void updateOwner(@PathVariable("ownerId") @Min(1) int ownerId, @Valid @RequestBody Owner ownerRequest) {
		ownerService.updateOwner(ownerId, ownerRequest);
	}
}
