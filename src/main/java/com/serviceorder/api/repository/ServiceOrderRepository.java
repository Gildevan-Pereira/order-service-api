package com.serviceorder.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.ServiceOrder;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

	@Query(value = "SELECT o FROM ServiceOrder o WHERE o.removedAt IS NULL AND o.uid = :uid") //JPQL
	Optional<ServiceOrder> findByUid(UUID uid);
}
