package com.serviceorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.serviceorder.api.entity.ServiceCategory;
import com.serviceorder.service.ServiceCategoryService;

@RestController
@RequestMapping("/categories")
public class ServiceCategoryController {
	
	@Autowired
	private ServiceCategoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		var result = service.findById(id);
		
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(result.get());
	}
	
	/*
	 * @GetMapping public String teste() {
	 * 
	 * return "Teste"; }
	 */
	

}
