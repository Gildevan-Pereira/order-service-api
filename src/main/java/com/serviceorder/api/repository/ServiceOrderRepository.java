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

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.domain.ServiceOrderStatus;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

	@Query(value = "SELECT so FROM ServiceOrder so WHERE so.removedAt IS NULL AND so.uid = :uid") //JPQL
	Optional<ServiceOrder> findByUid(UUID uid);
	
	@Query(value = "SELECT so FROM ServiceOrder so WHERE so.removedAt IS NULL") //JPQL
	Page<ServiceOrder> findAllByFilter(Pageable pageable); //Query Method JPA
	
	@Query(value = "SELECT so FROM ServiceOrder so WHERE so.removedAt IS NULL AND so.status IN :status AND (CAST(so.createdAt AS LocalDate) BETWEEN :start AND :end)") //Query JPQL
	List<ServiceOrder> findByStatusAndCreatedAtBetween(List<ServiceOrderStatus> status, LocalDate start, LocalDate end);
}
