package com.serviceorder.api.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.Technician;
import com.serviceorder.api.entity.dto.request.TechnicianCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.TechnicianBuilder;
import com.serviceorder.api.repository.TechnicianRepository;

@Service
public class TechnicianService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TechnicianRepository repository;
	
	@Autowired
	public AddressService addressService;
	
	public Technician create(TechnicianCreateReqDTO request) {
		
		var newAddress = addressService.create(request.getAddress());
		
		var newTechnician = TechnicianBuilder.build(request);
		
		newTechnician.setAddress(newAddress);
		
		return repository.save(newTechnician);
	}
	

}
