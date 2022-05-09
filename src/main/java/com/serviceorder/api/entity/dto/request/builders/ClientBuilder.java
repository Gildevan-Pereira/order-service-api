package com.serviceorder.api.entity.dto.request.builders;

import com.serviceorder.api.entity.Client;
import com.serviceorder.api.entity.dto.request.ClientCreateReqDTO;

public class ClientBuilder {
	
	public static Client build(ClientCreateReqDTO clientDTO) {
		
		return Client.builder()
				.fullname(clientDTO.getFullname())
				.email(clientDTO.getEmail())
				.identity(clientDTO.getIdentity())
				.phone(clientDTO.getPhone())
				.build();
	}
}
