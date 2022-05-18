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
	private AddressService service;

	@PostMapping
	public ResponseEntity<Address> create(@Valid @RequestBody AddressCreateReqDTO request) {
		
		var newEndereco = service.create(request);
		
		return ResponseEntity.ok(newEndereco);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<Address> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{uid}") //Endpoint for get Service Order by uid
	public ResponseEntity<Address> findByUid(@PathVariable("uid") UUID uid){
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(service.findByUid(uid));
	}
	
	@GetMapping
	public ResponseEntity<List<Address>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
}
