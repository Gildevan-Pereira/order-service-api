package com.serviceorder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<Service> create(@RequestBody ServiceCreateReqDTO request){
	  var newService = service.create(request);
	return ResponseEntity.ok(newService);
	}
}
