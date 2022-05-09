package com.serviceorder.api.entity.dto.response.builders;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class ClientResDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID id;
	private LocalDateTime createdAt;
	private String fullname;
	private String identity;
	private String phone;
	private String email;
	private AddressResDTO address;
	
}
