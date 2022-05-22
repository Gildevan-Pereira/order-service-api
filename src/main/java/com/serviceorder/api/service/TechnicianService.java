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

import com.serviceorder.api.entity.Technician;
import com.serviceorder.api.entity.builders.TechnicianBuilder;
import com.serviceorder.api.entity.dto.TechnicianCreateReqDTO;
import com.serviceorder.api.exceptions.CustomException;
import com.serviceorder.api.message.Messages;
import com.serviceorder.api.repository.AddressRepository;
import com.serviceorder.api.repository.TechnicianRepository;
import com.serviceorder.api.util.RemoveAccentsUtil;

@Service
public class TechnicianService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TechnicianRepository repository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	public AddressService addressService;
	
	public Technician findByUid(UUID uid) {
		return repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.TECHNICIAN_NOT_FOUND));
	}	
	
	public Page<Technician> findAllByFilter(Pageable pageable) {
		return repository.findAllByFilter(pageable);
	}
	
	public List<Technician> findByKeyword(String keyword) {
		var tratedSt = RemoveAccentsUtil.removeAccents(keyword.toLowerCase());
		return repository.findByKeyword(tratedSt);
	}
	
	@Transactional
	public Technician create(TechnicianCreateReqDTO request) {
		
		var newAddress = addressService.create(request.getAddress());
		var newTechnician = TechnicianBuilder.build(request);
		
		newTechnician.setAddress(newAddress);
		
		return repository.save(newTechnician);
	}
	
	@Transactional
	public void remove(UUID uid) {
		var technician = findByUid(uid);
		var address = addressService.findByUid(technician.getAddress().getUid());
		
		technician.setRemovedAt(LocalDateTime.now());
		address.setRemovedAt(LocalDateTime.now());
		
		addressRepository.save(address);
		repository.save(technician);
	}
	
	@Transactional
	public Technician update(TechnicianCreateReqDTO request, UUID uid) {
		var technician = findByUid(uid);
		return updateFields(request, technician);
	}
	
	@Transactional  //This annotation ensures that all operations must be completed successfully
	private Technician updateFields(TechnicianCreateReqDTO dto, Technician technician) {
		technician.getAddress().setCity(dto.getAddress().getCity());
		technician.getAddress().setDistrict(dto.getAddress().getDistrict());
		technician.getAddress().setNumber(dto.getAddress().getNumber());
		technician.getAddress().setComplement(dto.getAddress().getComplement());
		technician.getAddress().setZipcode(dto.getAddress().getZipcode());
		technician.getAddress().setState(dto.getAddress().getState());
		technician.getAddress().setStreet(dto.getAddress().getStreet());
		
		var updAddress = addressService.update(technician.getAddress());
		
		technician.setFullname(dto.getFullname());
		technician.setEmail(dto.getEmail());
		technician.setIdentity(dto.getIdentity());
		technician.setPhone(dto.getPhone());
		technician.setRole(dto.getRole());
		technician.setCommission(dto.getCommission());
		technician.setAddress(updAddress);
		
		return repository.save(technician);
	}

}
