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

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.ClientCreateReqDTO;
import com.serviceorder.api.service.ClientService;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@PostMapping
	public ResponseEntity<Client> create(@Valid @RequestBody ClientCreateReqDTO request) {
		var newclient = service.create(request);
		return ResponseEntity.ok(newclient);
	} 
	
	@PutMapping("/{uid}")
	public ResponseEntity<Client> update(@Valid @RequestBody ClientCreateReqDTO createReqDTO, @PathVariable UUID uid) {
		return ResponseEntity.ok(service.update(createReqDTO, uid));
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<Client> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
		
	@GetMapping
	public ResponseEntity<Page<Client>> findAllByFilter(
			@PageableDefault(direction = Direction.DESC, sort ="createdAt",
			page =0, size = 24) Pageable pageable){
		return ResponseEntity.ok(service.findAllByFilter(pageable));
	}
	
	@GetMapping("/{uid}")
	public ResponseEntity<Client> findByUid(@PathVariable("uid") UUID uid){
		return ResponseEntity.ok(service.findByUid(uid));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Client>> findByKeyword(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(service.findByKeyword(keyword));
	}
}
