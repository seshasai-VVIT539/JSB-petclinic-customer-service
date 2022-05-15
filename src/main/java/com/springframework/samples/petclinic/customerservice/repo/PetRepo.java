package com.springframework.samples.petclinic.customerservice.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springframework.samples.petclinic.customerservice.models.Pet;
import com.springframework.samples.petclinic.customerservice.models.PetType;

@Repository
public interface PetRepo extends JpaRepository<Pet, Integer> {

	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	List<PetType> findPetTypes();

	@Query("FROM PetType ptype WHERE ptype.id = :typeId")
	Optional<PetType> findPetTypeById(@Param("typeId") int typeId);
}
