package com.serviceorder.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Technician;

@Repository
public interface TechnicianRepository  extends JpaRepository<Technician, Long> {

	Optional<Technician> findByUid(UUID uid);
}
