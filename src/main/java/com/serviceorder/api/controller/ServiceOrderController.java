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

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.domain.ServiceOrderStatus;
import com.serviceorder.api.entity.dto.ServiceOrderCreateReqDTO;
import com.serviceorder.api.service.ServiceOrderService;

@RestController
@RequestMapping("/v1/service-orders")
public class ServiceOrderController {

	@Autowired
	private ServiceOrderService service;
	
	@PostMapping
	public ResponseEntity<ServiceOrder> create(@Valid @RequestBody ServiceOrderCreateReqDTO createReqDTO){
		var newServiceOrder = service.create(createReqDTO);
		return ResponseEntity.ok(newServiceOrder);
	}
	
	@PutMapping("/start/{uid}")
	public ResponseEntity<ServiceOrder> start(@PathVariable UUID uid){
		var newDateStart = service.start(uid);
		return ResponseEntity.ok(newDateStart);
	}
	
	@PutMapping("/finish/{uid}")
	public ResponseEntity<ServiceOrder> finish(@PathVariable UUID uid){
		var newDateFinish = service.finish(uid);
		return ResponseEntity.ok(newDateFinish);
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<ServiceOrder> status(@RequestParam("status") ServiceOrderStatus status, @PathVariable("uid") UUID uid){
		var serviceOrder = service.changeStatus(status, uid);
		return ResponseEntity.ok(serviceOrder);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<ServiceOrder> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Page<ServiceOrder>> findAllByFilter(
			@PageableDefault(direction = Direction.DESC, sort ="createdAt",
			page =0, size = 24) Pageable pageable){
		return ResponseEntity.ok(service.findAllByFilter(pageable));
	}
		
	@GetMapping("/{uid}")
	public ResponseEntity<ServiceOrder> findByUid(@PathVariable("uid") UUID uid){
		var serviceOrder = service.findByUid(uid);
		return ResponseEntity.ok(serviceOrder);
	}
	
	@GetMapping("/between") 
	public ResponseEntity<List<ServiceOrder>> findByStatusAndCreatedAtBetween(
			@RequestParam(value = "status", required = false) ServiceOrderStatus status,
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		var allServiceOrder = service.findByStatusAndCreatedAtBetween(status, start, end);
		return ResponseEntity.ok(allServiceOrder);
	}
}
