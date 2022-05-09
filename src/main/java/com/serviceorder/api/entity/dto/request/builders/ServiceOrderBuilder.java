package com.serviceorder.api.entity.dto.request.builders;

import com.serviceorder.api.entity.ServiceOrder;
import com.serviceorder.api.entity.dto.request.ServiceOrderCreateReqDTO;

public class ServiceOrderBuilder {

	public static ServiceOrder builder(ServiceOrderCreateReqDTO serviceOrderDT) {
		
		return ServiceOrder.builder()
				.remarks(serviceOrderDT.getRemarks())
				.startedAt(serviceOrderDT.getStartedAt())
				.finishedAt(serviceOrderDT.getFinishedAt())
				.build();
	}
}
