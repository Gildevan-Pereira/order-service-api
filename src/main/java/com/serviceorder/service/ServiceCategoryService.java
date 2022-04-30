package com.serviceorder.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.repository.ServiceCategoryRepository;

@Service
public class ServiceCategoryService {
	
	@Autowired
	private ServiceCategoryRepository repository;
	
	public Optional<ServiceCategory> findById(Long id) {
		
		return repository.findById(id);
	}
}
