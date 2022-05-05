package com.serviceorder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Technician;

@Repository
public interface ThecnicianRepository  extends JpaRepository<Technician, Long> {

}
