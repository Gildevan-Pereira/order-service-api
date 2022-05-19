package com.serviceorder.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.ServiceOrder;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

	@Query(value = "SELECT so FROM ServiceOrder so WHERE so.removedAt IS NULL AND so.uid = :uid") //JPQL
	Optional<ServiceOrder> findByUid(UUID uid);
	
	@Query(value = "SELECT so FROM ServiceOrder so WHERE so.removedAt IS NULL") //JPQL
	List<ServiceOrder> findAll(); //Query Method JPA
	
	@Query(value = "SELECT so FROM ServiceOrder so WHERE (CAST(so.startedAt AS LocalDate)) BETWEEN :start AND :end") //Query JPQL
	List<ServiceOrder> findByStartedAtBetween(LocalDate start, LocalDate end);
	
	@Query(value = "SELECT so FROM ServiceOrder so WHERE (CAST(so.finishedAt AS LocalDate)) BETWEEN :start AND :end") //Query JPQL
	List<ServiceOrder> findByFinishedAtBetween(LocalDate start, LocalDate end);
}
