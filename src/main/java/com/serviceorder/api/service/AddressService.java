package com.serviceorder.api.service;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.entity.dto.request.AddressCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.AddressBuilder;
import com.serviceorder.api.repository.AddressRepository;

@Service
public class AddressService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AddressRepository repository;
	
	public Address findByUid(UUID uid) {
		// TODO: Construir tratamento de exceções quando o optional estiver vazio
		return repository.findByUid(uid).get();
	}
	
	public Address create(AddressCreateReqDTO request) {
		return repository.save(AddressBuilder.build(request));
	}
}
