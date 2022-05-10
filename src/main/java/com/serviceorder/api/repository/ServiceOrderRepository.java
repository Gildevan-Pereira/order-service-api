package com.serviceorder.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

	Optional<ServiceOrder> findByUid(UUID Uid);

	ServiceOrder save(ServiceOrderCreateReqDTO reqDTO);
}
