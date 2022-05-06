package com.serviceorder.api.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.entity.dto.request.AddressCreationRequest;
import com.serviceorder.api.repository.AddressRepository;

@Service
public class AddressService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AddressRepository repository;
	
	public Optional<Address> findById(Long id) {
		
		return repository.findById(id);
	}
	public Address create(AddressCreationRequest request) {
		
		var address = Address.builder()
				.street(request.getStreet())
				.city(request.getCity())
				.district(request.getDistrict())
				.number(request.getNumber())
				.zipcode(request.getZipcode())
				.state(request.getState())
				.complement(request.getComplement())
				.build();
		
		return repository.save(address);
	}
}
