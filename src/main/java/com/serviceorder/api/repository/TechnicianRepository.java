package com.serviceorder.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Technician;

@Repository
public interface TechnicianRepository  extends JpaRepository<Technician, Long> {

	Technician findByUid(UUID uid);
}
