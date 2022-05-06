package com.serviceorder.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.api.entity.dto.request.CategoryCreationRequest;
import com.serviceorder.api.repository.ServiceCategoryRepository;

@Service
public class ServiceCategoryService {
	
	@Autowired
	private ServiceCategoryRepository repo;
	
	public Optional<ServiceCategory> findById(Long id) {
		
		return repo.findById(id);
	}
	
	public ServiceCategory create(CategoryCreationRequest request) {
		
		var serviceCategory = ServiceCategory.builder()
				.name(request.getCategoryName())
				.build();
		
		return repo.save(serviceCategory);
		
		
		
	}
}
