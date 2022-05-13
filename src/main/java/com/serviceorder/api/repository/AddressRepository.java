package com.serviceorder.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
	Optional<Address> findByUid(UUID uid); //Query Method JPA
}
