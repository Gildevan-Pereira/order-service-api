package com.serviceorder.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	
	@Query(value = "SELECT a FROM Address a WHERE a.removedAt IS NULL AND a.uid = :uid") //JPQL
	Optional<Address> findByUid(UUID uid); //Query Method JPA
	
	@Query(value = "SELECT a FROM Address a WHERE a.removedAt IS NULL") //JPQL
	Page<Address> findAllByFilter(Pageable pageable); //Query Method JPA
}
