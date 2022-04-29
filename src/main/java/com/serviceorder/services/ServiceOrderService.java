package com.serviceorder.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.repositorys.ServiceOrderRepository;

@Service
public class ServiceOrderService {
	
	@Autowired
	private ServiceOrderRepository repo;
	
	public ServiceOrder buscar(Integer id) {
		Optional<ServiceOrder> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
