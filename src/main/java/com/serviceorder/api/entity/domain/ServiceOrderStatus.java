package com.serviceorder.api.entity.domain;

import lombok.Getter;

@Getter
public enum ServiceOrderStatus {

	P("Pending"),
	S("Started"),
	F("Finished"),
	C("Canceled");
	
	
	private String description;
	
	private ServiceOrderStatus(String description) {
		this.description = description;
	}
	
}
