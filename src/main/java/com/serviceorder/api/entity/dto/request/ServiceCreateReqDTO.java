package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class ServiceCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private UUID categoryId;
	private String title;
	private String description;
	private BigDecimal amount;
	private String remarks;
}
