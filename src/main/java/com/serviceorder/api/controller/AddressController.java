package com.serviceorder.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.service.AddressService;

@RestController
@RequestMapping("/v1/addresses")
public class AddressController {

	@Autowired
	private AddressService service;
	
	@GetMapping("/{uid}")
	public ResponseEntity<Address> findByUid(@PathVariable("uid") UUID uid){
		return ResponseEntity.ok(service.findByUid(uid));
	}
	
	@GetMapping
	public ResponseEntity<Page<Address>> findAllByFilter(
			@PageableDefault(direction = Direction.DESC, sort ="createdAt",
			page =0, size = 24) Pageable pageable){
		return ResponseEntity.ok(service.findAllByFilter(pageable));
	}
}
