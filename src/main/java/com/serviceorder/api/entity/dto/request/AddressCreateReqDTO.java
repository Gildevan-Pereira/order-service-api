package com.serviceorder.api.entity.dto.request;

import java.io.Serializable;

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
public class AddressCreateReqDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String street;
	private String number;
	private String district;
	private String zipcode;
	private String city;
	private String state;
	private String complement;

}
