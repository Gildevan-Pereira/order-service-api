package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.builders.ServiceOrderBuilder;
import com.serviceorder.api.entity.domain.ServiceOrderStatus;
import com.serviceorder.api.entity.dto.ServiceOrderCreateReqDTO;
import com.serviceorder.api.exceptions.CustomException;
import com.serviceorder.api.message.Messages;
import com.serviceorder.api.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServiceOrderRepository repository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private TechnicianService technicianService;

	@Autowired
	private ServiceService serviceService;
	
	public Page<ServiceOrder> findAllByFilter(Pageable pageable) {
		return repository.findAllByFilter(pageable);
	}

	public ServiceOrder findByUid(UUID uid) {
		return repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.SERVICE_ORDER_NOT_FOUND));
	}
	
	@Transactional
	public ServiceOrder create(ServiceOrderCreateReqDTO request) {  //Service for create a new service order

		var client = clientService.findByUid(request.getClientId());
		var technician = technicianService.findByUid(request.getTechnicianId());
		var service = serviceService.create(request.getService());

		var newServiceOrder = ServiceOrderBuilder.build(request);

		newServiceOrder.setClient(client);
		newServiceOrder.setTechnician(technician);
		newServiceOrder.setService(service);

		return repository.save(newServiceOrder);
	}
	
	public ServiceOrder changeStatus(ServiceOrderStatus status, UUID uid) { //Service for set started_at
		var serviceOrder = findByUid(uid);
		serviceOrder.setStatus(status);
		return repository.save(serviceOrder);
	}
	

	public ServiceOrder start(UUID uid) { 
		var serviceOrder = findByUid(uid);
		if (serviceOrder.getStartedAt() != null) {
			throw new CustomException(Messages.SERVICE_ALREADY_STARTED);
		}
			
		serviceOrder.setStartedAt(LocalDateTime.now());
		serviceOrder.setStatus(ServiceOrderStatus.S);
		
		return repository.save(serviceOrder);
	}
	
	public ServiceOrder finish(UUID uid) { //Service for set finished_at
		var serviceOrder = findByUid(uid);
		if (serviceOrder.getStartedAt() != null) {
			throw new CustomException(Messages.SERVICE_ALREADY_FINISHED);
		}
		
		serviceOrder.setFinishedAt(LocalDateTime.now());
		serviceOrder.setStatus(ServiceOrderStatus.F);
		
		return  repository.save(serviceOrder);
	}
	
	public List<ServiceOrder> findByStatusAndCreatedAtBetween(ServiceOrderStatus status, String start, String end) {
		
		List<ServiceOrderStatus> receivedStatus = new ArrayList<>();
		
		if (status != null) {
			receivedStatus.add(status);
		} else {
			receivedStatus.addAll(Arrays.asList(ServiceOrderStatus.values()));
		}
		
		var startDate = LocalDate.parse(start);
		var endDate = LocalDate.parse(end);
		return repository.findByStatusAndCreatedAtBetween(receivedStatus, startDate, endDate);
	}

	public void remove(UUID uid) { //Service for set removed_at
		ServiceOrder serviceOrder = findByUid(uid);
		serviceOrder.setRemovedAt(LocalDateTime.now());
		repository.save(serviceOrder);
	}

}
