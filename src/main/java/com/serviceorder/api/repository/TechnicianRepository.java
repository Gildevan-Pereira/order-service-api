package com.serviceorder.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Technician;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

	@Query(value = "SELECT t FROM Technician t WHERE t.removedAt IS NULL AND t.uid = :uid") //JPQL
	Optional<Technician> findByUid(UUID uid);
	
	@Query(value = "SELECT t FROM Technician t WHERE t.removedAt IS NULL") //JPQL
	Page<Technician> findAllByFilter(Pageable pageable); //Query Method JPA

	@Query(nativeQuery = true, value = "SELECT * FROM technician t "
			+ "WHERE LOWER(UNACCENT(t.fullname)) LIKE %:keyword% "
			+ "OR  LOWER(UNACCENT(t.identity)) LIKE %:keyword% "
			+ "OR  LOWER(UNACCENT(t.phone)) LIKE %:keyword% "
			+ "OR  LOWER(UNACCENT(t.email)) LIKE %:keyword% "
			+ "OR  LOWER(UNACCENT(t.role)) LIKE %:keyword% "
			+ "AND t.removed_at IS NULL")
	List<Technician> findByKeyword(String keyword);
}
