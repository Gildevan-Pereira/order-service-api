package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.serviceorder.api.entity.Service;
import com.serviceorder.api.entity.dto.request.ServiceCreateReqDTO;
import com.serviceorder.api.repository.ServiceCategoryRepository;
import com.serviceorder.api.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ServiceRepository repository;
	
	@Autowired
	private ServiceCategoryRepository categoryRepository;
	
	public Optional<Service> findByUid(UUID uid) {
		return repository.findByUid(uid);
	}
	
	public Service create(ServiceCreateReqDTO request) {
		
		var category = categoryRepository.findByUid(request.getCategoryId());
				
		var newService = Service.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.amount(request.getAmount())
			.remarks(request.getRemarks())
			.serviceCategory(category.get())
			.createdAt(LocalDateTime.now())
			.build();

		return repository.save(newService);
	}

}
