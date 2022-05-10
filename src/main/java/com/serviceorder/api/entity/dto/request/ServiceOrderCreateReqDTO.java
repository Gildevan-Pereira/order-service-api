package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceOrderCreateReqDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UUID uid;
	private ServiceCreateReqDTO service;
	private UUID clientId;
	private UUID technicianId;
	private String remarks;
}
