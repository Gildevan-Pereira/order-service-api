package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.ServiceOrderBuilder;
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
	
	public List<ServiceOrder> findAll() {
		return repository.findAll();
	}

	public ServiceOrder findByUid(UUID uid) {
		var serviceOrder = repository.findByUid(uid);
		
		if(serviceOrder.isEmpty()) {
			throw new CustomException(Messages.SERVICE_ORDER_NOT_FOUND);
		}
		return serviceOrder.get();
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

	public ServiceOrder start(UUID uid) { //Service for set started_at
		ServiceOrder serviceOrder = repository.findByUid(uid).get();
		if (serviceOrder.getStartedAt() == null)
			
			serviceOrder.setStartedAt(LocalDateTime.now());
		
		return repository.save(serviceOrder);
	}
	
	public ServiceOrder finishedAt(UUID uid) { //Service for set finished_at
		ServiceOrder serviceOrder = repository.findByUid(uid).get();
		if (serviceOrder.getFinishedAt() == null)
			
			serviceOrder.setFinishedAt(LocalDateTime.now());
		
		return  repository.save(serviceOrder);
		
	}
	
	public List<ServiceOrder> findByBetween(String start, String end) {
		var startDate = LocalDate.parse(start);
		var endDate = LocalDate.parse(end);
		return repository.findByStartedAtBetween(startDate, endDate);
	}

	public List<ServiceOrder> findByBetweenEnd(String start, String end) {
		var startDate = LocalDate.parse(start);
		var endDate = LocalDate.parse(end);
		return repository.findByFinishedAtBetween(startDate, endDate);
	}
	
	public void remove(UUID uid) { //Service for set removed_at
		ServiceOrder serviceOrder = repository.findByUid(uid).get();
		if (serviceOrder.getRemovedAt() == null)
			
			serviceOrder.setRemovedAt(LocalDateTime.now());
		
		repository.save(serviceOrder);
	}

}
