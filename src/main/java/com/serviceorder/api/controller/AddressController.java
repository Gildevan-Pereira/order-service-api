package com.serviceorder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.entity.dto.request.AddressCreateReqDTO;
import com.serviceorder.api.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService address;

//	@GetMapping("/{id}")
//	public ResponseEntity<?> get(@PathVariable("id") Long id) {
//		var result = address.findById(id);
//
//		if (result.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		}
//
//		return ResponseEntity.ok(result.get());
//
//	}

	@PostMapping
	public ResponseEntity<Address> create(@RequestBody AddressCreateReqDTO request) {
		
		var newEndereco = address.create(request);
		
		return ResponseEntity.ok(newEndereco);
	}
}
