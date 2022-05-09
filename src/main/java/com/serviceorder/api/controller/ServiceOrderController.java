package com.serviceorder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
}
