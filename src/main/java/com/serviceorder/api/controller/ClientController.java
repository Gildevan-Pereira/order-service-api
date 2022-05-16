package com.serviceorder.api.controller;

import java.util.List;
import java.util.UUID;

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

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.request.ClientCreateReqDTO;
import com.serviceorder.api.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	//FIXME: Criar DTO de Response para "NÃO" retornar a ENTITY, bem como o BUILDER
	@PostMapping
	public ResponseEntity<Client> clientCreate(@RequestBody ClientCreateReqDTO request) {
		var newclient = service.clientCreate(request);
		return ResponseEntity.ok(newclient);
	} 
	
	@PutMapping("/{uid}")
	public ResponseEntity<Client> clientUpdate(@RequestBody ClientCreateReqDTO createReqDTO, @PathVariable UUID uid) {
		return ResponseEntity.ok(service.clientUpdate(createReqDTO, uid));
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<Client> delete(@PathVariable("uid") UUID uid) {
		service.remove(uid);
		return ResponseEntity.noContent().build();
	}
		
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{uid}") //Endpoint for get Service Order by uid
	public ResponseEntity<Client> findByUid(@PathVariable("uid") UUID uid){
		//TODO: Substituir o tipo de retorno do ResponseEntity para ServiceOrderResDTO
		return ResponseEntity.ok(service.findByUid(uid));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Client>> findByKeyword(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok(service.findByKeyword(keyword));
	}
	


}
