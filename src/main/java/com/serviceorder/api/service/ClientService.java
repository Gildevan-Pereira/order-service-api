package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import com.serviceorder.api.util.RemoveAccentsUtil;

@Service
public class ClientService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	public ClientRepository repository;

	@Autowired
	public AddressService addressService;
	

	public Client findByUid(UUID uid) {
		// TODO: Construir tratamento de exceções quando o optional estiver vazio
		return repository.findByUid(uid).get();
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public List<Client> findByKeyword(String keyword) {
		var tratedStr = RemoveAccentsUtil.removeAccents(keyword.toLowerCase());
		return repository.findByKeyword(tratedStr);
	}
	
	public Client clientCreate(ClientCreateReqDTO request) {

		var newAddress = addressService.create(request.getAddress());

		var newClient = ClientBuilder.build(request);

		newClient.setAddress(newAddress);

		return repository.save(newClient);
	}
	
	public void remove(UUID uid) { //Service for set removed_at
		Client client = repository.findByUid(uid).get();
		if (client.getRemovedAt() == null)
			
			client.setRemovedAt(LocalDateTime.now());
		
		repository.save(client);
	}
	
	public Client clientUpdate(ClientCreateReqDTO request, UUID uid) {
		Optional<Client> client = repository.findByUid(uid);
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
		
		return repository.save(client);
	}
}
