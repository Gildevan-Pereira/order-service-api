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
public class ClientCreateReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fullname;
	private String identity;
	private String phone;
	private String email;
	private AddressCreateReqDTO address;
	
}
