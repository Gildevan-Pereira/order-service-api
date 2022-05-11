package com.serviceorder.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping //Endpoint for create an Service Order
	public ResponseEntity<ServiceOrder> serviceOrder(@RequestBody ServiceOrderCreateReqDTO createReqDTO){
		var newServiceOrder = orderService.create(createReqDTO);
		
		return ResponseEntity.ok(newServiceOrder);
	}
	
	@PutMapping("/start/{uid}") //Endpoint for set started_at in the table
	public ResponseEntity<ServiceOrder> setStarded(@PathVariable UUID uid){
		var newDateStart = orderService.start(uid);
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(newDateStart);
	}
	
	@PutMapping("/finish/{uid}") //Endpoint for set finished_at in the table
	public ResponseEntity<ServiceOrder> setFinish(@PathVariable UUID uid){
		var newDateFinish = orderService.finish(uid);
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(newDateFinish);
	}
	
	@GetMapping("/{uid}") //Endpoint for get Service Order by uid
	public ResponseEntity<ServiceOrder> buscar(@PathVariable("uid") UUID uid){
		var serviceOrder = orderService.buscar(uid);
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(serviceOrder);
	}
		
	
}
