package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.entity.builders.AddressBuilder;
import com.serviceorder.api.entity.dto.AddressCreateReqDTO;
import com.serviceorder.api.exceptions.CustomException;
import com.serviceorder.api.message.Messages;
import com.serviceorder.api.repository.AddressRepository;

@Service
public class AddressService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AddressRepository repository;
	
	public Page<Address> findAllByFilter(Pageable pageable) {
		return repository.findAllByFilter(pageable);
	}
	
	public Address findByUid(UUID uid) {
		return repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.ADDRESS_NOT_FOUND));
	}
	
	public Address create(AddressCreateReqDTO request) {
		return repository.save(AddressBuilder.build(request));
	}
	
	public Address update(Address address) {
		return repository.save(address);
	}
	
	public Address remove(UUID uid) {
		var address = findByUid(uid);
		address.setRemovedAt(LocalDateTime.now());
		return repository.save(address);
	}
}
