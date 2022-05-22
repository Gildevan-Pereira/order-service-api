package com.serviceorder.api.entity.builders;

import java.time.LocalDateTime;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.domain.ServiceOrderStatus;
import com.serviceorder.api.entity.dto.ServiceOrderCreateReqDTO;

public class ServiceOrderBuilder {

	public static ServiceOrder build(ServiceOrderCreateReqDTO serviceOrderDT) {
		
		return ServiceOrder.builder()
				.remarks(serviceOrderDT.getRemarks())
				.createdAt(LocalDateTime.now())
				.status(ServiceOrderStatus.P)
				.build();
	}
}
