package com.serviceorder.api.service;

import java.io.Serializable;
import java.util.List;
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
	
	public Client findByUid(UUID uid) {
		// TODO: Construir tratamento de exceções quando o optional estiver vazio
		return clientRepository.findByUid(uid).get();
	}
		
	public Client create(ClientCreateReqDTO request) {
		
		var newAddress = addressService.create(request.getAddress());
		
		var newClient = ClientBuilder.build(request);
		
		newClient.setAddress(newAddress);
				
		return clientRepository.save(newClient);
	}
	
	public List<Client> findByFullname(String fullname) {
		
		return clientRepository.findByFullname(fullname);
		
	}
	
	

	
}
