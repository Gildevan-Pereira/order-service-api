package com.serviceorder.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.api.entity.dto.CategoryCreateReqDTO;
import com.serviceorder.api.service.ServiceCategoryService;

@RestController
@RequestMapping("/v1/categories")
public class ServiceCategoryController {
	
	@Autowired
	private ServiceCategoryService service;
	
	@PostMapping
	public ResponseEntity<ServiceCategory> create(@Valid @RequestBody CategoryCreateReqDTO request) {
		var category = service.create(request);
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<ServiceCategory> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ServiceCategory>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<ServiceCategory> findByUid(@PathVariable("uid") UUID uid) {
		var result = service.findByUid(uid);
		return ResponseEntity.ok(result);
	}	
	
	@GetMapping("/search")
	public ResponseEntity<List<ServiceCategory>> findByName(@RequestParam("name") String name) {
		return  ResponseEntity.ok(service.findByName(name));
	}
}
