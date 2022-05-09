package com.serviceorder.api.entity.dto.request.builders;

import com.serviceorder.api.entity.Technician;
import com.serviceorder.api.entity.dto.request.TechnicianCreateReqDTO;

public class TechnicianBuilder {
	
	public static Technician build(TechnicianCreateReqDTO tecDto) {
		
		return Technician.builder()
				.fullname(tecDto.getFullname())
				.email(tecDto.getEmail())
				.identity(tecDto.getIdentity())
				.phone(tecDto.getPhone())
				.role(tecDto.getRole())
				.commission(tecDto.getCommission())
				.build();
	}
}
