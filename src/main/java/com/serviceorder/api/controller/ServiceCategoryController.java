package com.serviceorder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.api.entity.dto.request.CategoryCreationRequest;
import com.serviceorder.api.entity.dto.response.ServiceCategoryDTO;
import com.serviceorder.api.service.ServiceCategoryService;


@RestController
@RequestMapping("/categories")
public class ServiceCategoryController {
	
	@Autowired
	private ServiceCategoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ServiceCategory> find(@PathVariable("id") Long id) {
		var result = service.findById(id);
		
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(result.get());
	}	
	
	@PostMapping
	public ResponseEntity<ServiceCategory> create(@RequestBody CategoryCreationRequest request) {
		
		var category = service.create(request);
		
		return ResponseEntity.ok(category);
		
	}

}
