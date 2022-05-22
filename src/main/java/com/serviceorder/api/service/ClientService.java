package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.builders.ClientBuilder;
import com.serviceorder.api.entity.dto.ClientCreateReqDTO;
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
		return repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.CLIENT_NOT_FOUND));
	}

	public Page<Client> findAllByFilter(Pageable pageable) {
		return repository.findAllByFilter(pageable);
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
	public void remove(UUID uid) {
		var client = findByUid(uid);
		var address = addressService.findByUid(client.getAddress().getUid());
		
		client.setRemovedAt(LocalDateTime.now());
		address.setRemovedAt(LocalDateTime.now());
			
		addressRepository.save(address);
		repository.save(client);
	}
	
	@Transactional
	public Client update(ClientCreateReqDTO request, UUID uid) {
		var client = findByUid(uid);
			return updateFields(request, client);
	}
	
	@Transactional
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
