package com.serviceorder.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;
import com.serviceorder.api.service.ServiceOrderService;

@RestController
@RequestMapping("/serviceorder")
public class ServiceOrderController {

	@Autowired
	private ServiceOrderService orderService;
	
	@PostMapping
	public ResponseEntity<ServiceOrder> serviceOrder(@RequestBody ServiceOrderCreateReqDTO createReqDTO){
		var newServiceOrder = orderService.create(createReqDTO);
		
		return ResponseEntity.ok(newServiceOrder);
	}
	
	@PatchMapping("/{uid}")
	public ResponseEntity<ServiceOrder> setDate(@PathVariable UUID uid){
		var newDateStart = orderService.start(uid);
		
		return ResponseEntity.ok(newDateStart);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<ServiceOrder> buscar(@PathVariable("id") UUID uid){
		var serviceOrder = orderService.buscar(uid);
		
		return ResponseEntity.ok(serviceOrder);
	}
	
}
