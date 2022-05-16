package com.serviceorder.api.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

	@Query(value = "SELECT s FROM Service s WHERE s.removedAt IS NULL AND s.uid = :uid") //JPQL
	Optional<Service> findByUid(UUID uid); 
	
//	@Query(nativeQuery = true, value = "SELECT * FROM service s "
//			+ "WHERE s.title LIKE %:keyword% OR s.description LIKE %:keyword%")
//	List<Service> findAllByKeyword(String keyword);
	
	@Query(nativeQuery = true, value = "SELECT * FROM service s "
			+ "WHERE LOWER(UNACCENT(s.title)) LIKE %:keyword%")
	List<Service> findAllByKeyword(String keyword);
	
	@Query(value = "SELECT s FROM Service s WHERE (CAST(s.createdAt AS LocalDate)) BETWEEN :start AND :end") //Query JPQL
	List<Service> findByDateBetweenStart(LocalDate start, LocalDate end);
	
	@Query(value = "SELECT s FROM Service s WHERE (CAST(s.removedAt AS LocalDate)) BETWEEN :start AND :end") //Query JPQL
	List<Service> findByDateBetweenEnd(LocalDate start, LocalDate end);
	
	List<Service> findByAmount(BigDecimal amount);
}
