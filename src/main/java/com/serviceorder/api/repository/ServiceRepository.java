package com.serviceorder.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

	@Query(value = "SELECT s FROM Service s WHERE s.removedAt IS NULL AND s.uid = :uid") //JPQL
	Optional<Service> findByUid(UUID uid); 
	
	@Query(value = "SELECT s FROM Service s WHERE s.removedAt IS NULL") //JPQL
	Page<Service> findAllByFilter(Pageable pageable); //Query Method JPA
	
	@Query(nativeQuery = true, value = "SELECT * FROM service s "
			+ "WHERE LOWER(UNACCENT(s.title)) LIKE %:keyword% "
			+ "OR LOWER(UNACCENT(s.description)) LIKE %:keyword%"
			+ "AND s.removed_at IS NULL")
	List<Service> findAllByKeyword(String keyword);
	
	@Query(value = "SELECT s FROM Service s WHERE s.removedAt IS NULL AND (CAST(s.createdAt AS LocalDate)) BETWEEN :start AND :end") //Query JPQL
	List<Service> findByDateBetweenStart(LocalDate start, LocalDate end);
	
	@Query(value = "SELECT s FROM Service s WHERE s.removedAt IS NULL AND (CAST(s.removedAt AS LocalDate)) BETWEEN :start AND :end") //Query JPQL
	List<Service> findByDateBetweenEnd(LocalDate start, LocalDate end);
	
}
