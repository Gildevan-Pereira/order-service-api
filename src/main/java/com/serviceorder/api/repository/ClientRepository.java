package com.serviceorder.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByUid(UUID uid);
	
	List<Client> findByFullname(String name);
}
