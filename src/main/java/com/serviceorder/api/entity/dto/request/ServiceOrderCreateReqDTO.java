package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	
	private ServiceCreateReqDTO service;
	private ClientCreateReqDTO client;
	private TechnicianCreateReqDTO technician;
	private LocalDateTime startedAt;
	private LocalDateTime finishedAt;
	private String remarks;
}
