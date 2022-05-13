package com.serviceorder.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

	Optional<Technician> findByUid(UUID uid);

	@Query(nativeQuery = true, value = "SELECT * FROM technician t  "
			+ "WHERE (t.fullname LIKE %:keyword% "
			+ "	OR t.identity LIKE %:keyword% "
			+ "	OR t.phone LIKE %:keyword%)"
			+ "OR t.phone LIKE %:keyword% "
			+ "OR t.email LIKE %:keyword% "
			+ "OR t.role LIKE %:keyword% "
			+ "		AND t.removed_at IS NULL ") // JPQL
	List<Technician> findByKeyword(String keyword);
}
