package com.serviceorder.api.service;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.request.ClientCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.ClientBuilder;
import com.serviceorder.api.repository.ClientRepository;

@Service
public class ClientService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	public ClientRepository clientRepository;
	
	@Autowired
	public AddressService addressService;
	
	
	public Optional<Client> findByUid(UUID uid) {
		return clientRepository.findByUid(uid);
	}
	
	public Client create(ClientCreateReqDTO request) {
		
		var newAddress = addressService.create(request.getAddress());
		
		var newClient = ClientBuilder.build(request);
		
		newClient.setAddress(newAddress);
				
		return clientRepository.save(newClient);
		
	}
	
}
