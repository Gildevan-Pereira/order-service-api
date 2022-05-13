package com.serviceorder.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.api.entity.dto.request.CategoryCreateReqDTO;
import com.serviceorder.api.repository.ServiceCategoryRepository;

@Service
public class ServiceCategoryService {
	
	@Autowired
	private ServiceCategoryRepository repo;
	
	public ServiceCategory findByUid(UUID uid) {
		// TODO: Construir tratamento de exceções quando o optional estiver vazio
		//return obj.orElseThrow(() -> new ObjectNotFoundException(uid, null);)
		return repo.findByUid(uid).get();
	}
	
	public List<ServiceCategory> findAll() {
		return repo.findAll();
	}
	
	public ServiceCategory create(CategoryCreateReqDTO request) {
		
		var serviceCategory = ServiceCategory.builder()
				.name(request.getCategoryName())
				.build();
		
		return repo.save(serviceCategory);
		
		
		
	}
}
