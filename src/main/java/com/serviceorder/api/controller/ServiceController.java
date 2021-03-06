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

import com.serviceorder.api.entity.Service;
import com.serviceorder.api.entity.dto.ServiceCreateReqDTO;
import com.serviceorder.api.service.ServiceService;

@RestController
@RequestMapping("/v1/services")
public class ServiceController {

	@Autowired
	public ServiceService service;

	@PostMapping
	public ResponseEntity<Service> create(@Valid @RequestBody ServiceCreateReqDTO request) {
		var newService = service.create(request);
		return ResponseEntity.ok(newService);
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Service> update(@Valid @RequestBody ServiceCreateReqDTO createReqDTO, @PathVariable UUID uid) {
		var update = service.update(createReqDTO, uid);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<Service> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<Service>> findAllByFilter(
			@PageableDefault(direction = Direction.DESC, sort ="createdAt",
			page =0, size = 24) Pageable pageable){
		return ResponseEntity.ok(service.findAllByFilter(pageable));
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Service> findByUid(@PathVariable("uid") UUID uid) {
		return ResponseEntity.ok(service.findByUid(uid));
	}

	@GetMapping("/search") 
	public ResponseEntity<List<Service>> findByKeyword(@RequestParam("keyword") String keyword) {
		var allService = service.findByKeyword(keyword);
		return ResponseEntity.ok(allService);
	}

	@GetMapping("/between") 
	public ResponseEntity<List<Service>> findByCreatedAtBetween(
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		var allService = service.findByCreatedAtBetween(start, end);
		return ResponseEntity.ok(allService);
	}
}
