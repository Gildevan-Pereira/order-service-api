package com.serviceorder.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.Technician;
import com.serviceorder.api.entity.dto.TechnicianCreateReqDTO;
import com.serviceorder.api.service.TechnicianService;

@RestController
@RequestMapping("/v1/technicians")
public class TechnicianController {
	
	@Autowired
	private TechnicianService service;
	
	@PostMapping
	public ResponseEntity<Technician> create(@Valid @RequestBody TechnicianCreateReqDTO request) {
		return ResponseEntity.ok(service.create(request));
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Technician> update(@Valid @RequestBody TechnicianCreateReqDTO createReqDTO, @PathVariable UUID uid) {
		var update = service.update(createReqDTO, uid);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<Technician> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
		
	@GetMapping
	public ResponseEntity<Page<Technician>> findAllByFilter(
			@PageableDefault(direction = Direction.DESC, sort ="createdAt",
			page =0, size = 24) Pageable pageable){
		return ResponseEntity.ok(service.findAllByFilter(pageable));
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Technician> findByUid(@PathVariable("uid") UUID uid){
		return ResponseEntity.ok(service.findByUid(uid));
	}
	
	@GetMapping("search")
	public ResponseEntity<List<Technician>> findByKeyword(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(service.findByKeyword(keyword));
	}
}
