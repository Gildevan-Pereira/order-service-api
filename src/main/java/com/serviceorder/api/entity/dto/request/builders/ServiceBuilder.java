package com.serviceorder.api.entity.dto.request.builders;

import com.serviceorder.api.entity.Service;
import com.serviceorder.api.entity.dto.request.ServiceCreateReqDTO;

public class ServiceBuilder {

public static Service build(ServiceCreateReqDTO seReqDTO) {
		
		return Service.builder()
				.title(seReqDTO.getTitle())
				.description(seReqDTO.getDescription())
				.amount(seReqDTO.getAmount())
				.remarks(seReqDTO.getRemarks())
				.build();
	}
}
