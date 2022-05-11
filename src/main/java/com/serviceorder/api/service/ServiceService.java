package com.serviceorder.api.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

	public Service findByUid(UUID uid) {
		return repository.findByUid(uid).get();
	}

	public List<Service> findByKeyword(String keyword) {
		return repository.findAllByKeyword(keyword);
	}
	
	public List<Service> findByAmount(BigDecimal amount){
		return repository.findByAmount(amount);
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

	public Service create(ServiceCreateReqDTO request) {

		var category = categoryRepository.findByUid(request.getCategoryId());

		var newService = Service.builder().title(request.getTitle()).description(request.getDescription())
				.amount(request.getAmount()).remarks(request.getRemarks()).serviceCategory(category.get())
				.createdAt(LocalDateTime.now()).build();

		return repository.save(newService);
	}

}
