package com.serviceorder.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.api.entity.dto.CategoryCreateReqDTO;
import com.serviceorder.api.exceptions.CustomException;
import com.serviceorder.api.message.Messages;
import com.serviceorder.api.repository.ServiceCategoryRepository;
import com.serviceorder.api.util.RemoveAccentsUtil;

@Service
public class ServiceCategoryService {
	
	@Autowired
	private ServiceCategoryRepository repository;
	
	public ServiceCategory create(CategoryCreateReqDTO request) {
		var serviceCategory = ServiceCategory.builder()
				.name(request.getName())
				.build();
		return repository.save(serviceCategory);
	}
	
	public void remove(UUID uid) {
		ServiceCategory category = findByUid(uid);
		category.setRemovedAt(LocalDateTime.now());
		repository.save(category);
	}
	
	public ServiceCategory findByUid(UUID uid) {
		return repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.CATEGORY_NOT_FOUND));
	}
	
	public List<ServiceCategory> findAll() {
		return repository.findAll();
	}
	
	public List<ServiceCategory> findByName(String name) {
		var tratedSt = RemoveAccentsUtil.removeAccents(name.toLowerCase());
		return repository.findByName(tratedSt.toLowerCase());
	}
	
	
}
