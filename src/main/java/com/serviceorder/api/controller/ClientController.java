package com.serviceorder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.request.ClientCreateReqDTO;
import com.serviceorder.api.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	//FIXME: Criar DTO de Response para "NÃO" retornar a ENTITY, bem como o BUILDER
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody ClientCreateReqDTO request) {
		var newclient = clientService.create(request);
		
		return ResponseEntity.ok(newclient);
	} 
	


}
