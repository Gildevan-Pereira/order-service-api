package com.serviceorder.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceOrderRepository repo;
	
	public ServiceOrder buscar(Integer id) {
		Optional<ServiceOrder> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
