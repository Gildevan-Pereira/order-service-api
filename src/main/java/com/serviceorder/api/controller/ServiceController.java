package com.serviceorder.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.Service;
import com.serviceorder.api.entity.dto.request.ServiceCreateReqDTO;
import com.serviceorder.api.service.ServiceService;

@RestController
@RequestMapping(value = "/service")
public class ServiceController {

	@Autowired
	public ServiceService service;

	@PostMapping
	public ResponseEntity<Service> create(@RequestBody ServiceCreateReqDTO request) {
		var newService = service.create(request);
		return ResponseEntity.ok(newService);
	}

	@GetMapping("/keyword") 
	public ResponseEntity<List<Service>> findByKeyword(@RequestParam("keyword") String keyword) {
		var allService = service.findByKeyword(keyword);
		// TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(allService);
	}

	@GetMapping("/between") 
	public ResponseEntity<List<Service>> findByDateBetween(
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		var allService = service.findByBetween(start, end);
		// TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(allService);
	}
	@GetMapping("/between/end") 
	public ResponseEntity<List<Service>> findByDateBetweenEnd(
			@RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) {
		var allService = service.findByBetweenEnd(start, end);
		// TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(allService);
	}
	
	@GetMapping("/amount")
	public ResponseEntity<List<Service>> findByAmount(@RequestParam("amount") BigDecimal amount){
		var allService = service.findByAmount(amount);
		//TODO: Set any method for return estimated value 
		return ResponseEntity.ok(allService);
	}
}
