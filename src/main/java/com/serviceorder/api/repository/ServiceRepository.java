package com.serviceorder.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Service;

@Repository
public interface ServiceRepository  extends JpaRepository<Service, Long> {

	Service findByUid(UUID uid);
}
