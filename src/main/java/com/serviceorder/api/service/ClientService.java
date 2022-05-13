package com.serviceorder.api.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

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

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public List<Client> findByKeyword(String keyword) {
		return clientRepository.findByKeyword(keyword);
	}
	
	public Client clientCreate(ClientCreateReqDTO request) {

		var newAddress = addressService.create(request.getAddress());

		var newClient = ClientBuilder.build(request);

		newClient.setAddress(newAddress);

		return clientRepository.save(newClient);
	}
	
	public Client clientUpdate(ClientCreateReqDTO request, UUID uid) {
		Optional<Client> client = clientRepository.findByUid(uid);
		if(client.isPresent()) {
			return updateFields(request, client.get());
		}
		
		return null;
	}
	
	@Transactional   //This annotation ensures that all operations must be completed successfully
	private Client updateFields(ClientCreateReqDTO dto, Client client) {
		client.getAddress().setCity(dto.getAddress().getCity());
		client.getAddress().setDistrict(dto.getAddress().getDistrict());
		client.getAddress().setNumber(dto.getAddress().getNumber());
		client.getAddress().setComplement(dto.getAddress().getComplement());
		client.getAddress().setZipcode(dto.getAddress().getZipcode());
		client.getAddress().setState(dto.getAddress().getState());
		client.getAddress().setStreet(dto.getAddress().getStreet());
		
		var updAddress = addressService.update(client.getAddress());
		
		client.setFullname(dto.getFullname());
		client.setEmail(dto.getEmail());
		client.setIdentity(dto.getIdentity());
		client.setPhone(dto.getPhone());
		client.setAddress(updAddress);
		
		return clientRepository.save(client);
	}
}
