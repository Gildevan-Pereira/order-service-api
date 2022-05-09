package com.serviceorder.api.entity.dto.request.builders;

import com.serviceorder.api.entity.Address;
import com.serviceorder.api.entity.dto.request.AddressCreateReqDTO;

public class AddressBuilder {
	
	public static Address build(AddressCreateReqDTO addressDto) {
		
		return Address.builder()
				.street(addressDto.getStreet())
				.city(addressDto.getCity())
				.district(addressDto.getDistrict())
				.number(addressDto.getNumber())
				.zipcode(addressDto.getZipcode())
				.state(addressDto.getState())
				.complement(addressDto.getComplement())
				.build();
	}

}
