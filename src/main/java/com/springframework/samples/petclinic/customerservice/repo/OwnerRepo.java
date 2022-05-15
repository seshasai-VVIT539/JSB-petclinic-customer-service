package com.springframework.samples.petclinic.customerservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springframework.samples.petclinic.customerservice.models.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer> {

}
