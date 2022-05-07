package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.serviceorder.api.entity.Service;
import com.serviceorder.api.entity.ServiceCategory;
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
	
	public Service create(ServiceCreateReqDTO request) {
		
		var category = ServiceCategory.builder()
				.name(request.getCategory().getName())
				.build();
		
		var savedCategory = categoryRepository.save(category);
		
		var newService = Service.builder()
			.title(request.getTitle())
			.description(request.getDescription())
			.amount(request.getAmount())
			.remarks(request.getRemarks())
			.serviceCategory(savedCategory)
			.createdAt(LocalDateTime.now())
			.build();

		var x  = repository.save(newService);
		
		return x;
		
	}

}
