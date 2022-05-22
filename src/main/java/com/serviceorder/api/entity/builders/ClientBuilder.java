package com.serviceorder.api.entity.builders;

import java.time.LocalDateTime;

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.ClientCreateReqDTO;

public class ClientBuilder {
	
	public static Client build(ClientCreateReqDTO clientDTO) {
		
		return Client.builder()
				.fullname(clientDTO.getFullname())
				.email(clientDTO.getEmail())
				.identity(clientDTO.getIdentity())
				.phone(clientDTO.getPhone())
				.createdAt(LocalDateTime.now())
				.build();
	}
}
