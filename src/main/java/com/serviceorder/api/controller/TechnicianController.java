package com.serviceorder.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.Technician;
import com.serviceorder.api.entity.dto.request.TechnicianCreateReqDTO;
import com.serviceorder.api.service.TechnicianService;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {
	
	@Autowired
	private TechnicianService service;
	
	@PostMapping
	public ResponseEntity<Technician> create(@RequestBody TechnicianCreateReqDTO request) {
		return ResponseEntity.ok(service.create(request));
	}
	
	@GetMapping("keyword")
	public ResponseEntity<List<Technician>> findByKeyword(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(service.findByKeyword(keyword));
	}
}
