package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.ServiceOrderBuilder;
import com.serviceorder.api.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ServiceOrderRepository repository;

//	@Autowired
//	private ServiceOrderService orderService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private TechnicianService technicianService;

	@Autowired
	private ServiceService serviceService;

	public ServiceOrder buscar(UUID uid) {
		Optional<ServiceOrder> obj = repository.findByUid(uid);
		return obj.orElse(null);
	}

	public ServiceOrder create(ServiceOrderCreateReqDTO request) {

		var client = clientService.findByUid(request.getClientId());
		var technician = technicianService.findByUid(request.getTechnicianId());
		var service = serviceService.create(request.getService());

		var newServiceOrder = ServiceOrderBuilder.build(request);

		newServiceOrder.setClient(client);
		newServiceOrder.setTechnician(technician);
		newServiceOrder.setService(service);

		return repository.save(newServiceOrder);
	}

//	public ServiceOrder startedAt(ServiceOrderCreateReqDTO request) {	
//		
//		var serviceOrder = orderService.buscar(request.getUid());
//		
//		serviceOrder.setStartedAt(LocalDateTime.now());;
//		
//		return repository.save(serviceOrder);
//	}

	public ServiceOrder start(UUID uid) {
		ServiceOrder serviceOrder = repository.findByUid(uid).get();
		if (serviceOrder.getStartedAt() == null) {
			serviceOrder.setStartedAt(LocalDateTime.now());
		}
		return serviceOrder;
	}
}
