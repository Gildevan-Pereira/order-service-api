package com.serviceorder.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.api.entity.dto.request.CategoryCreateReqDTO;
import com.serviceorder.api.repository.ServiceCategoryRepository;
import com.serviceorder.api.util.RemoveAccentsUtil;

@Service
public class ServiceCategoryService {
	
	@Autowired
	private ServiceCategoryRepository repository;
	
	public ServiceCategory findByUid(UUID uid) {
		// TODO: Construir tratamento de exceções quando o optional estiver vazio
		//return obj.orElseThrow(() -> new ObjectNotFoundException(uid, null);)
		return repository.findByUid(uid).get();
	}
	
	public List<ServiceCategory> findAll() {
		return repository.findAll();
	}
	
	public List<ServiceCategory> findByName(String name) {
		var tratedSt = RemoveAccentsUtil.removeAccents(name.toLowerCase());
		return repository.findByName(tratedSt.toLowerCase());
	}
	
	public void remove(UUID uid) { //Service for set removed_at
		ServiceCategory category = repository.findByUid(uid).get();
		if (category.getRemovedAt() == null)
			
			category.setRemovedAt(LocalDateTime.now());
		
		repository.save(category);
	}
	
	public ServiceCategory create(CategoryCreateReqDTO request) {
		
		var serviceCategory = ServiceCategory.builder()
				.name(request.getName())
				.build();
		
		return repository.save(serviceCategory);
		
		
		
	}
}
