package com.serviceorder.api.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.ServiceOrderBuilder;
import com.serviceorder.api.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceOrderRepository repository;
	
	@Autowired
	private ClientService client;
	
	@Autowired
	private TechnicianService technician;
	
	@Autowired
	private ServiceService service;
	
	public ServiceOrder buscar(UUID uid) {
		Optional<ServiceOrder> obj = repository.findByUid(uid);
		return obj.orElse(null);
	}
	
	
	public ServiceOrder create(ServiceOrderCreateReqDTO request) {	
		
//		var clientId = client.findByUid(request.getClient().getUid());
//		var technicianId = technician.findByUid(request.getTechnician().getUid());
//		var idTech = technicianId.get().getId();
//		var serviceId = service.findByUid(request.getService().getUid());
//		var idService = serviceId.get().getId();
		var newServiceOrder = ServiceOrderBuilder.builder(request);

		newServiceOrder.getClient().getId();
		newServiceOrder.getTechnician().getId();
		newServiceOrder.getService().getId();
		
		return repository.save(newServiceOrder);
	}
	
}
