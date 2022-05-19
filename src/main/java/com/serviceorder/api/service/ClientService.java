package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.request.ClientCreateReqDTO;
import com.serviceorder.api.entity.dto.request.builders.ClientBuilder;
import com.serviceorder.api.exceptions.CustomException;
import com.serviceorder.api.message.Messages;
import com.serviceorder.api.repository.AddressRepository;
import com.serviceorder.api.repository.ClientRepository;
import com.serviceorder.api.util.RemoveAccentsUtil;

@Service
public class ClientService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	public ClientRepository repository;
	
	@Autowired
	public AddressRepository addressRepository;

	@Autowired
	public AddressService addressService;
	

	public Client findByUid(UUID uid) {
		var client = repository.findByUid(uid);
		if(client.isEmpty()) {
			throw new CustomException(Messages.CLIENT_NOT_FOUND);
		}
		return client.get();
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
	
	public List<Client> findByKeyword(String keyword) {
		var tratedStr = RemoveAccentsUtil.removeAccents(keyword.toLowerCase());
		return repository.findByKeyword(tratedStr);
	}
	
	@Transactional
	public Client create(ClientCreateReqDTO request) {

		var newAddress = addressService.create(request.getAddress());

		var newClient = ClientBuilder.build(request);

		newClient.setAddress(newAddress);

		return repository.save(newClient);
	}
	
	@Transactional
	public void remove(UUID uid) { //Service for set removed_at
		Client client = repository.findByUid(uid).get();
		Address address = addressService.findByUid(client.getAddress().getUid());
		if (client.getRemovedAt() == null || address.getRemovedAt() == null)
			
			client.setRemovedAt(LocalDateTime.now());
			address.setRemovedAt(LocalDateTime.now());
			
		addressRepository.save(address);
		repository.save(client);
	}
	
	@Transactional
	public Client update(ClientCreateReqDTO request, UUID uid) {
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
