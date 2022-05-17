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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;
import com.serviceorder.api.service.ServiceOrderService;

@RestController
@RequestMapping("/serviceorder")
public class ServiceOrderController {

	@Autowired
	private ServiceOrderService service;
	
	@PostMapping
	public ResponseEntity<ServiceOrder> create(@Valid @RequestBody ServiceOrderCreateReqDTO createReqDTO){
		var newServiceOrder = service.create(createReqDTO);
		return ResponseEntity.ok(newServiceOrder);
	}
	
	@PutMapping("/start/{uid}")
	public ResponseEntity<ServiceOrder> setStartedAt(@PathVariable UUID uid){
		var newDateStart = service.start(uid);
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(newDateStart);
	}
	
	@PutMapping("/finish/{uid}")
	public ResponseEntity<ServiceOrder> setFinishedAt(@PathVariable UUID uid){
		var newDateFinish = service.finishedAt(uid);
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(newDateFinish);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<ServiceOrder> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ServiceOrder>> findAll(){
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(service.findAll());
	}
		
	@GetMapping("/{uid}")
	public ResponseEntity<ServiceOrder> findByUid(@PathVariable("uid") UUID uid){
		var serviceOrder = service.findByUid(uid);
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(serviceOrder);
	}
	
	@GetMapping("/between") 
	public ResponseEntity<List<ServiceOrder>> findByDateBetween(
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		var allServiceOrder = service.findByBetween(start, end);
		return ResponseEntity.ok(allServiceOrder);
	}
	
	@GetMapping("/between/end") 
	public ResponseEntity<List<ServiceOrder>> findByDateBetweenEnd(
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		var allServiceOrder = service.findByBetweenEnd(start, end);
		return ResponseEntity.ok(allServiceOrder);
	}
		
	
}
