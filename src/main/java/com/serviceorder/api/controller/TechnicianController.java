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

import com.serviceorder.api.entity.Technician;
import com.serviceorder.api.entity.dto.request.TechnicianCreateReqDTO;
import com.serviceorder.api.service.TechnicianService;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {
	
	@Autowired
	private TechnicianService service;
	
	@PostMapping
	public ResponseEntity<Technician> technicianCreat(@Valid @RequestBody TechnicianCreateReqDTO request) {
		return ResponseEntity.ok(service.technicianCreat(request));
	}
	
	@PutMapping("/{uid}")
	public ResponseEntity<Technician> technicianUpdate(@Valid @RequestBody TechnicianCreateReqDTO createReqDTO, @PathVariable UUID uid) {
		var update = service.technicianUpdate(createReqDTO, uid);
		return ResponseEntity.ok(update);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<Technician> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
		
	@GetMapping
	public ResponseEntity<List<Technician>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{uid}") //Endpoint for get Service Order by uid
	public ResponseEntity<Technician> findByUid(@PathVariable("uid") UUID uid){
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(service.findByUid(uid));
	}
	
	@GetMapping("search")
	public ResponseEntity<List<Technician>> findByKeyword(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(service.findByKeyword(keyword));
	}
}
