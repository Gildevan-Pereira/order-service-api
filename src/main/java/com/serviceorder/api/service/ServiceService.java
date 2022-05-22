package com.serviceorder.api.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.serviceorder.api.entity.Service;
import com.serviceorder.api.entity.builders.ServiceBuilder;
import com.serviceorder.api.entity.dto.ServiceCreateReqDTO;
import com.serviceorder.api.exceptions.CustomException;
import com.serviceorder.api.message.Messages;
import com.serviceorder.api.repository.ServiceRepository;
import com.serviceorder.api.util.RemoveAccentsUtil;

@org.springframework.stereotype.Service
public class ServiceService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ServiceRepository repository;

	@Autowired
	private ServiceCategoryService categoryService;
	
	public Service findByUid(UUID uid) {
		return repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.SERVICE_NOT_FOUND));
	}
	
	public Page<Service> findAllByFilter(Pageable pageable) {
		return repository.findAllByFilter(pageable);
	}
	
	@Transactional
	public Service create(ServiceCreateReqDTO request) {
		var category = categoryService.findByUid(request.getCategoryId());
		var newService = ServiceBuilder.build(request);
		newService.setServiceCategory(category);
		return repository.save(newService);
	}


	public List<Service> findByKeyword(String keyword) {
		var tratedSt = RemoveAccentsUtil.removeAccents(keyword.toLowerCase());
		return repository.findAllByKeyword(tratedSt);
	}
	
	public List<Service> findByBetween(String start, String end) {
		var startDate = LocalDate.parse(start);
		var endDate = LocalDate.parse(end);
		return repository.findByDateBetweenStart(startDate, endDate);
	}

	public List<Service> findByBetweenEnd(String start, String end) {
		var startDate = LocalDate.parse(start);
		var endDate = LocalDate.parse(end);
		return repository.findByDateBetweenEnd(startDate, endDate);
	}

	public void remove(UUID uid) {
		Service service = repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.SERVICE_NOT_FOUND));
		service.setRemovedAt(LocalDateTime.now());
		repository.save(service);
	}
	
	@Transactional
	public Service update(ServiceCreateReqDTO request, UUID uid) {
		var service = repository.findByUid(uid)
				.orElseThrow(() -> new CustomException(Messages.SERVICE_NOT_FOUND));
			return updateFields(request, service);
	}
	
	@Transactional   //This annotation ensures that all operations must be completed with successfully
	private Service updateFields(ServiceCreateReqDTO dto, Service service) {
		
		var category = categoryService.findByUid(dto.getCategoryId());
		
		service.setTitle(dto.getTitle());
		service.setDescription(dto.getDescription());
		service.setAmount(dto.getAmount());
		service.setRemarks(dto.getRemarks());
		service.setServiceCategory(category);
		
		return repository.save(service);
	}

}
